package com.accumulate.zuul;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.MultipartConfigElement;
/**
 * Created by  hukaijia.
 * Description:
 * Date: 2017-05-27-14:48
 */
@SpringBootApplication
@EnableDiscoveryClient
//@EnableFeignClients
@EnableZuulProxy
public class ZuulApplication {
    private static final Logger log = LoggerFactory.getLogger(ZuulApplication.class);

    public static void main(String[] args) {
        new SpringApplicationBuilder(ZuulApplication.class).web(true).run(args);
    }

//    @Autowired
//    private MultipartProperties multipartProperties;

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowCredentials(true);
        corsConfig.addAllowedOrigin("*");
        corsConfig.addAllowedHeader("*");
        corsConfig.addExposedHeader("X-pangolin-error");
        corsConfig.addExposedHeader("X-pangolin-alert");
        corsConfig.addExposedHeader("X-pangolin-params");
        corsConfig.addAllowedMethod("OPTIONS");
        corsConfig.addAllowedMethod("HEAD");
        corsConfig.addAllowedMethod("GET");
        corsConfig.addAllowedMethod("PUT");
        corsConfig.addAllowedMethod("POST");
        corsConfig.addAllowedMethod("DELETE");
        corsConfig.addAllowedMethod("PATCH");
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfig);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
//    @Bean
//    @ConditionalOnMissingBean
//    public MultipartConfigElement multipartConfigElement() {
//        this.multipartProperties.setMaxFileSize("1024MB");
//        this.multipartProperties.setMaxRequestSize("1024MB");
//        return this.multipartProperties.createMultipartConfig();
//    }
}



