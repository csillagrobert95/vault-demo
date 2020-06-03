package com.rcsillag.vault.gateway.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Service for calling external REST endpoint.
 */
@Service
public class GatewayService {
    /**
     * Base URL to call.
     */
    private static final String URL = "https://10.128.0.3:8443";

    /**
     * The RestTemplate to use.
     */
    private RestTemplate restTemplate;

    /**
     * Constructor.
     */
    public GatewayService() {
        restTemplate = new RestTemplate();
    }

    /**
     * Call external service and return the response String.
     * @param name The name to greet.
     * @return The response String.
     */
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
