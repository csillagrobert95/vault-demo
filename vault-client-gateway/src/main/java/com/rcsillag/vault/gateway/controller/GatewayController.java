package com.rcsillag.vault.gateway.controller;

import com.rcsillag.vault.gateway.service.GatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class provides the methods for handling incoming requests.
 */
@RestController
@RequestMapping(value="/gateway")
public class GatewayController {
    /**
     * The GatewayService to use for forwarding requests.
     */
    private GatewayService gatewayService;

    /**
     * Sets the gatewayService to the value of the gatewayService parameter.
     * @param gatewayService The GatewayService to set.
     */
    @Autowired
    public void setGatewayService(GatewayService gatewayService) {
        this.gatewayService = gatewayService;
    }

    /**
     * Forward the request parameter and call the service.
     * @param name The name to greet.
     * @return The greeting.
     */
    @RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
    public String forwardSayHello(@PathVariable(name="name")String name){
        return gatewayService.sayHello(name);
    }
}
