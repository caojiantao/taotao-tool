package com.taotao.tool.job;

import com.aliyun.alidns20150109.models.*;
import com.taotao.tool.util.JsonUtils;
import com.taotao.tool.yml.AliYml;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Component
public class DnsRefreshJob {

    @Autowired
    private AliYml aliYml;

    private com.aliyun.alidns20150109.Client client = null;

    private static final Pattern pattern = Pattern.compile("<dd class=\"fz24\">(.*?)</dd>");


    @Scheduled(fixedRate = 5, timeUnit = TimeUnit.MINUTES)
    public void dnsRefreshJob() throws Exception {
        // 每次都尝试初始化 client，避免断电重启后由于网络因素导致 PostConstruct 失败
        initClient();
        if (Objects.isNull(client)) {
            // 未配置阿里云秘钥
            return;
        }
        List<DescribeDomainRecordsResponseBody.DescribeDomainRecordsResponseBodyDomainRecordsRecord> recordList = getRecordList();
        String ip = getCurrentIp();
        for (DescribeDomainRecordsResponseBody.DescribeDomainRecordsResponseBodyDomainRecordsRecord record : recordList) {
            if (Objects.equals(ip, record.getValue())) {
                log.info("act=domainAnalysisJob rr={} desc=公网IP暂无变化", record.getRR());
                continue;
            }
            UpdateDomainRecordRequest request = new UpdateDomainRecordRequest();
            request.setRecordId(record.getRecordId());
            request.setRR(record.getRR());
            request.setType(record.getType());
            request.setValue(ip);
            updateDomainRecord(request);
        }
    }

    public com.aliyun.alidns20150109.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                // 必填，您的 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 必填，您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "alidns.cn-hangzhou.aliyuncs.com";
        return new com.aliyun.alidns20150109.Client(config);
    }

    private String getCurrentIp() {
        Mono<String> mono = WebClient.create()
                .method(HttpMethod.GET)
                .uri("https://ip.chinaz.com/")
                .retrieve()
                .bodyToMono(String.class);
        String html = mono.block();
        assert html != null;
        Matcher m = pattern.matcher(html);
        String ip = m.find() ? m.group(1) : null;
        log.info("act=getIp ip={}", ip);
        return ip;
    }

    private List<DescribeDomainRecordsResponseBody.DescribeDomainRecordsResponseBodyDomainRecordsRecord> getRecordList() {
        DescribeDomainRecordsResponse response = getDomainRecords(aliYml.getDomain());
        return Optional.ofNullable(response).map(DescribeDomainRecordsResponse::getBody)
                .map(DescribeDomainRecordsResponseBody::getDomainRecords)
                .map(DescribeDomainRecordsResponseBody.DescribeDomainRecordsResponseBodyDomainRecords::getRecord)
                .orElse(new ArrayList<>())
                .stream()
                .filter(item -> aliYml.getRrList().contains(item.getRR()))
                .collect(Collectors.toList());
    }

    public DescribeDomainRecordsResponse getDomainRecords(String domainName) {
        DescribeDomainRecordsRequest req = new DescribeDomainRecordsRequest();
        req.domainName = domainName;
        try {
            DescribeDomainRecordsResponse resp = client.describeDomainRecords(req);
            log.info("act=domainRecords domain={} resp={}", domainName, JsonUtils.toJson(resp));
            return resp;
        } catch (Exception e) {
            log.error("act=domainRecords", e);
        }
        return null;
    }

    public void updateDomainRecord(UpdateDomainRecordRequest req) {
        try {
            UpdateDomainRecordResponse resp = client.updateDomainRecord(req);
            log.info("act=updateDomainRecord resp={}", JsonUtils.toJson(resp));
        } catch (Exception e) {
            log.error("act=updateDomainRecord", e);
        }
    }

    @PostConstruct
    public void initClient() {
        if (!StringUtils.hasText(aliYml.getKey()) || !StringUtils.hasText(aliYml.getSecret())) {
            return;
        }
        if (Objects.nonNull(this.client)) {
            return;
        }
        try {
            this.client = createClient(aliYml.getKey(), aliYml.getSecret());
        } catch (Exception e) {
            log.error("创建万网 client 异常", e);
        } finally {
            log.error("创建万网 client 结果: {}", Objects.nonNull(client));
        }
    }
}
