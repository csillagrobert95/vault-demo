package com.rcsillag.vault.gateway.controller;

import com.rcsillag.vault.gateway.service.GatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Robi on 5/29/2020.
 */
@RestController
@RequestMapping(value="/gateway")
public class GatewayController {
    private GatewayService gatewayService;

    @Autowired
    public void setGatewayService(GatewayService gatewayService) {
        this.gatewayService = gatewayService;
    }

    @RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
    public String forwardSayHello(@PathVariable(name="name")String name){
        return gatewayService.sayHello(name);
    }
}
