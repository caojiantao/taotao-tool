package com.taotao.tool.job;

import com.aliyun.alidns20150109.Client;
import com.aliyun.alidns20150109.models.*;
import com.taotao.tool.util.JsonUtils;
import com.taotao.tool.yml.AliYml;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
public class DomainAnalysisJob {

    @Autowired
    private AliYml aliYml;

    private static final Pattern pattern = Pattern.compile("<dd class=\"fz24\">(.*?)</dd>");


    @Scheduled(fixedRate = 5, timeUnit = TimeUnit.MINUTES)
    public void domainAnalysisJob() throws Exception {
        if (Objects.isNull(aliYml.getKey()) || Objects.isNull(aliYml.getSecret())) {
            log.info("act=domainAnalysisJob desc=未配置阿里云秘钥");
            return;
        }
        com.aliyun.alidns20150109.Client client = createClient(aliYml.getKey(), aliYml.getSecret());
        DescribeDomainRecordsResponseBody.DescribeDomainRecordsResponseBodyDomainRecordsRecord record = getRecord(client);
        String ip = getIp();
        if (Objects.equals(ip, record.getValue())) {
            log.info("act=domainAnalysisJob desc=公网IP暂无变化");
            return;
        }
        UpdateDomainRecordRequest request = new UpdateDomainRecordRequest();
        request.setRecordId(record.getRecordId());
        request.setRR(record.getRR());
        request.setType(record.getType());
        request.setValue(ip);
        updateDomainRecord(client, request);
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

    private String getIp() {
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

    private DescribeDomainRecordsResponseBody.DescribeDomainRecordsResponseBodyDomainRecordsRecord getRecord(Client client) throws Exception {
        String domain = "caojiantao.site";
        String rr = "tmp";
        DescribeDomainRecordsResponse response = DomainAnalysisJob.domainRecords(client, domain);
        return Optional.ofNullable(response).map(DescribeDomainRecordsResponse::getBody)
                .map(DescribeDomainRecordsResponseBody::getDomainRecords)
                .map(DescribeDomainRecordsResponseBody.DescribeDomainRecordsResponseBodyDomainRecords::getRecord)
                .orElse(new ArrayList<>())
                .stream()
                .filter(item -> Objects.equals(rr, item.getRR()))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    public static DescribeDomainRecordsResponse domainRecords(com.aliyun.alidns20150109.Client client, String domainName) {
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

    public void updateDomainRecord(com.aliyun.alidns20150109.Client client, UpdateDomainRecordRequest req) {
        try {
            UpdateDomainRecordResponse resp = client.updateDomainRecord(req);
            log.info("act=updateDomainRecord resp={}", JsonUtils.toJson(resp));
        } catch (Exception e) {
            log.error("act=updateDomainRecord", e);
        }
    }
}
