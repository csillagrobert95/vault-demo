package com.jlr.rcsillag.vault.gateway.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Robi on 5/29/2020.
 */
@Service
public class GatewayService {
    private static final String URL = "https://10.128.0.3:8443";

    private RestTemplate restTemplate;

    public GatewayService() {
        restTemplate = new RestTemplate();
    }

    public String sayHello(String name){
        String responseString;
        try {
            String helloUrl = URL + "/hello/" + name ;
            ResponseEntity<String> response
                    = restTemplate.getForEntity(helloUrl, String.class);
            responseString =  response.getBody();
        }catch (Exception e){
            responseString = "Something went wrong: " + e.getMessage();
            e.printStackTrace();
        }
        return responseString;
    }
}
