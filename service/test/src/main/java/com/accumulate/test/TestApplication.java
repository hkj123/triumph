package com.accumulate.test;

import com.accumulate.core.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;

@EnableEurekaClient
@EnableFeignClients      //Feign
@EnableDiscoveryClient   //注册服务负载均衡
@SpringBootApplication
@EnableCircuitBreaker    //断路器功能
@EnableHystrixDashboard   //启动断路器仪表盘功能
@EnableJpaRepositories(basePackages = {"com.accumulate.test.repository", "com.accumulate.entity"})
@EntityScan("com.accumulate.entity")
public class TestApplication {
    private static final Logger log = LoggerFactory.getLogger(TestApplication.class);

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    //    消息队列
    @Bean
    public Queue unReduceSuccessQueue() {
        return new Queue(Constants.DATAINFO_CONFIRM_QE);
    }

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(TestApplication.class);
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
    //    http://localhost:9004/hystrix.stream    单个监控
}
