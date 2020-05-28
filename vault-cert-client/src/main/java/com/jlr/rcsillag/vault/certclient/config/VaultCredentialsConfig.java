package com.jlr.rcsillag.vault.certclient.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by Robi on 5/28/2020.
 */
@ConfigurationProperties("vault")
public class VaultCredentialsConfig {
    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
