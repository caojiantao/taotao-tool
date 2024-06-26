package com.taotao.tool.system.job;

import com.aliyun.alidns20150109.Client;
import com.aliyun.alidns20150109.models.*;
import com.taotao.tool.common.util.JsonUtils;
import com.taotao.tool.system.yml.AliYml;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.Duration;
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
        log.info("act=createClient type=start");
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                // 必填，您的 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 必填，您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "alidns.cn-hangzhou.aliyuncs.com";
        Client client = new Client(config);
        log.info("act=createClient type=end client={}", client);
        return client;
    }

    private String getCurrentIp() {
        Mono<String> mono = WebClient.create()
                .method(HttpMethod.GET)
                .uri("http://checkip.amazonaws.com/")
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofSeconds(10));
        String result = mono.block();
        assert result != null;
        String ip = result.trim();
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
        try {
            if (!StringUtils.hasText(aliYml.getKey()) || !StringUtils.hasText(aliYml.getSecret())) {
                log.info("act=initClient type=noConfig");
                return;
            }
            if (Objects.nonNull(this.client)) {
                log.info("act=initClient type=hasClient");
                return;
            }
            this.client = createClient(aliYml.getKey(), aliYml.getSecret());
        } catch (Exception e) {
            log.error("创建万网 client 异常", e);
        } finally {
            log.info("创建万网 client 结果: {}", Objects.nonNull(client));
        }
    }
}
