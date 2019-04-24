package com.accumulate.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.InetAddress;
import java.net.UnknownHostException;

@EnableEurekaClient
@EnableDiscoveryClient
@EnableSwagger2
//@SpringBootApplication
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
//@EnableFeignClients      //Feign
@ComponentScan(basePackages = {
        "com.accumulate.business.config",
        "com.accumulate.business.controller",
        "com.accumulate.business.service"})
public class BusinessApplication {
    private static final Logger log = LoggerFactory.getLogger(BusinessApplication.class);

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

//    //    消息队列
//    @Bean
//    public Queue unReduceSuccessQueue() {
//        return new Queue(Constants.DATAINFO_CONFIRM_QE);
//    }

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(BusinessApplication.class);
        Environment env = app.run(args).getEnvironment();
        log.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "Local: \t\thttp://localhost:{}\n\t" +
                        "External: \thttp://{}:{}\n\t" +
                        "SwaggerUI: \thttp://localhost:{}/swagger-ui.html\n" +
                        "----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                env.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),
                env.getProperty("server.port"));
    }
}
