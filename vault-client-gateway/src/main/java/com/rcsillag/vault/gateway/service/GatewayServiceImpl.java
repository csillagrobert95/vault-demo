package com.rcsillag.vault.gateway.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Service for calling external REST endpoint.
 */
@Service
public class GatewayServiceImpl implements GatewayService {
    /**
     * External service base URL.
     */
    @Value("${external.service.base.url:http://localhost:8443}")
    private String baseUrl;

    /**
     * The RestTemplate to use.
     */
    private RestTemplate restTemplate;

    /**
     * Constructor.
     */
    public GatewayServiceImpl() {
        restTemplate = new RestTemplate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String sayHello(String name){
        String responseString;
        try {
            String helloUrl = baseUrl + "/hello/" + name ;
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
