package com.rcsillag.vault.gateway;

import com.rcsillag.vault.gateway.config.VaultCertificateConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Main class of the vault-client-gateway module.
 */
@SpringBootApplication
@EnableConfigurationProperties(VaultCertificateConfig.class)
public class VaultClientGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(VaultClientGatewayApplication.class, args);
	}

}
