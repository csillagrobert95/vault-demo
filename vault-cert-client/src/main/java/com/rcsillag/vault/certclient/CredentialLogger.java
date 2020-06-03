package com.rcsillag.vault.certclient;

import com.rcsillag.vault.certclient.config.VaultCredentialsConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by Robi on 5/28/2020.
 */
@Component
public class CredentialLogger implements CommandLineRunner {
    private VaultCredentialsConfig configuration;


    @Autowired
    public CredentialLogger(VaultCredentialsConfig configuration) {
        this.configuration = configuration;
    }

    @Override
    public void run(String... args) {

        Logger logger = LoggerFactory.getLogger(CredentialLogger.class);

        logger.info("----------------------------------------");
        logger.info("Configuration properties");
        logger.info("   vault.username is {}", configuration.getUsername());
        logger.info("   vault.password is {}", configuration.getPassword());
        logger.info("----------------------------------------");
    }
}
