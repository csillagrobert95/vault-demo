package com.rcsillag.vault.certclient.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Domain model for TLS certificate data stored in Vault.
 */
@ConfigurationProperties("cert")
public class VaultCertificateConfig {
    private String serialNumber;

    private String certificate;

    private String issuingCaCertificate;

    private String privateKey;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getIssuingCaCertificate() {
        return issuingCaCertificate;
    }

    public void setIssuingCaCertificate(String issuingCaCertificate) {
        this.issuingCaCertificate = issuingCaCertificate;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
}
