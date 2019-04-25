package com.accumulate.business.config.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by DELL on 2019/4/25.
 */

@Configuration
@Endpoint(id = "db")
public class MyDbEndpoint {

    @Autowired
    private RestTemplate restTemplate;

    @ReadOperation
    public Map<String, Object> endpoint() {
        Health health = new Health();
        try {
            health = restTemplate.getForObject("http://business-service:9001/metrics/health", Health.class);
            if (Objects.nonNull(health)) {
                Map<String, Object> result = new HashMap<>();
                result.put("type", "db");
                result.put("status", health.getDetails().getDb().getStatus());
                return result;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw e;
        }
    }
    //    http://localhost:9001/metrics/redis
}
