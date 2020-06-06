package com.rcsillag.vault.certclient;

import com.rcsillag.vault.certclient.config.VaultCredentialsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Main class of the vault-cert-client module.
 */
@SpringBootApplication
@EnableConfigurationProperties(VaultCredentialsConfig.class)
public class VaultCertClientApplication {
	public static void main(String[] args) {
		SpringApplication.run(VaultCertClientApplication.class, args);
	}
}
