package com.rcsillag.vault.certclient.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Provides the functionality for logging the configuration stored in Vault, usually credentials.
 * DON'T USE THIS IN PRODUCTION!
 * It only serves as a test class for spring-cloud-starter-vault-config.
 */
@Component
public class VaultConfigurationLogger implements CommandLineRunner {
    /**
     * The VaultCredentialsConfig configuration.
     */
    private VaultCredentialsConfig configuration;

    /**
     * Constructor
     * @param configuration The VaultCredentialsConfig configuration which will be used.
     */
    @Autowired
    public VaultConfigurationLogger(VaultCredentialsConfig configuration) {
        this.configuration = configuration;
    }

    /**
     * Log configuration properties stored in Vault at application startup.
     * @param args String arguments.
     */
    @Override
    public void run(String... args) {
        Logger logger = LoggerFactory.getLogger(VaultConfigurationLogger.class);

        logger.info("----------------------------------------");
        logger.info("Configuration properties");
        logger.info("   vault.username is {}", configuration.getUsername());
        logger.info("   vault.password is {}", configuration.getPassword());
        logger.info("----------------------------------------");
    }
}
