package com.accumulate.eureka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
//@SpringBootApplication
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class EurekaApplication {
    private static final Logger log = LoggerFactory.getLogger(EurekaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class, args);
//        new SpringApplicationBuilder(EurekaApplication.class).run(args);
    }
}
//		http://localhost:1111/
