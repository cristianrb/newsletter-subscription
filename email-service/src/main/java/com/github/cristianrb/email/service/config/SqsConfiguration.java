package com.github.cristianrb.email.service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("services.sqs.credentials")
@Component
public class SqsConfiguration {
    private String access_key;
    private String secret_access_key;
    private String region;
    private String endpoint;
    private String uri;

    public String getAccess_key() {
        return access_key;
    }

    public void setAccess_key(String access_key) {
        this.access_key = access_key;
    }

    public String getSecret_access_key() {
        return secret_access_key;
    }

    public void setSecret_access_key(String secret_access_key) {
        this.secret_access_key = secret_access_key;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
}
