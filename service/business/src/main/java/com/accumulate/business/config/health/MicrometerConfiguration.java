package com.accumulate.business.config.health;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by DELL on 2019/4/22.
 * #prometheus+grafana+springboot2监控集成配置
 */

@Configuration
public class MicrometerConfiguration {
    @org.springframework.beans.factory.annotation.Value("${spring.application.name}")
    private  String application;

    @Bean
    MeterRegistryCustomizer meterRegistryCustomizer(MeterRegistry meterRegistry) {
        return meterRegistry1 -> {
            meterRegistry.config()
                    .commonTags("application", application);
        };
    }
//    http://localhost:9001/actuator/prometheus  查看健康状况
    //http://localhost:9001/metrics/metrics/hikaricp.connections.active
}
