package com.rcsillag.vault.gateway.service;

/**
 * This interface defines the methods for forwarding requests to external services.
 */
public interface GatewayService {
    /**
     * Call external greeting service and return the response String.
     * @param name The name to greet.
     * @return The response String.
     */
    String sayHello(String name);
}
