package com.delicacy.cookies.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Objects;

/**
 * RestTempalte工具
 *
 * @author linzhenghui
 * @date 2021/04/19
 */
@Slf4j
public class RestTemplateUtils {

    public static JSONObject get(RestTemplate restTemplate, String url) {
        HttpHeaders headers = new HttpHeaders();
        // 假设是用json
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<String> entity = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        String body = entity.getBody();
        return JSONObject.parseObject(body);
    }

    public static JSONObject getWithHandlers(RestTemplate restTemplate, String url, HttpHeaders headers) {
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<String> entity = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        String body = entity.getBody();
        return JSONObject.parseObject(body);
    }

    public static JSONObject post(RestTemplate restTemplate , String url, Map<String,Object> params){
        ResponseEntity<JSONObject> response = restTemplate.postForEntity(url, params, JSONObject.class);
        HttpStatus statusCode = response.getStatusCode();
        if (statusCode.value() != HttpStatus.OK.value()) {
            return null;
        }else{
            return response.getBody();
        }
    }

    public static JSONObject post(RestTemplate restTemplate,String url,Map<String,Object> params,Map<String,Object> headerMap){
        HttpHeaders headers = new HttpHeaders();

        if(Objects.nonNull(headerMap)) {
            for (Map.Entry<String,Object> entry : headerMap.entrySet()) {
                headers.add(entry.getKey(),Objects.isNull(entry.getValue()) ? "" : entry.getValue().toString());
            }
        }

        HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(params, headers);
        ResponseEntity<String> entity = restTemplate.postForEntity(url, request, String.class);
        String body = entity.getBody();
        return JSONObject.parseObject(body);
    }

}
