package com.delicacy.cookies.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


/**
 * httpTemplate
 * @author linzhenghui
 * @date 2020/7/4
 */

@Configuration
public class RestTemplateConfig {

    @Bean("restTemplate")
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean("urlConnectionRestTemplate")
    public RestTemplate urlConnectionRestTemplate(){
        return new RestTemplate(new SimpleClientHttpRequestFactory());
    }

    @Bean("httpClientRestTemplate")
    public RestTemplate httpClientRestTemplate(){
        return new RestTemplate(new HttpComponentsClientHttpRequestFactory());
    }

    @Bean("oKHttp3RestTemplate")
    public RestTemplate oKHttp3RestTemplate(){
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }

}
