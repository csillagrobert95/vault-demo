package com.rcsillag.vault.gateway.tls;

import com.rcsillag.vault.gateway.config.VaultCertificateConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.SslStoreProvider;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.CertificateBundle;

import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

/**
 * Bean for customizing SSL/TLS certificate management.
 */
@Component
public class SslCustomizationBean implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {
    /**
     * The VaultTemplate to use for accessing Hashicorp Vault.
     */
    private VaultTemplate vaultTemplate;

    /**
     * The TLS certificate data.
     */
    private VaultCertificateConfig vaultCertificateConfig;

    /**
     * Constructor.
     * @param vaultTemplate The VaultTemplate to use.
     * @param vaultCertificateConfig The TLS certificate data to use.
     */
    public SslCustomizationBean(@Autowired VaultTemplate vaultTemplate,
                                @Autowired VaultCertificateConfig vaultCertificateConfig) {
        this.vaultTemplate = vaultTemplate;
        this.vaultCertificateConfig = vaultCertificateConfig;
    }

    /**
     * Customize the KeyStore and TrustStore.
     * @param factory The ConfigurableServletWebServerFactory.
     */
    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {
        factory.setSslStoreProvider(new SslStoreProvider() {
            @Override
            public KeyStore getKeyStore() {
                CertificateBundle appCertBundle = CertificateBundle.of(vaultCertificateConfig.getSerialNumber(),
                        vaultCertificateConfig.getCertificate(),
                        vaultCertificateConfig.getIssuingCaCertificate(),
                        vaultCertificateConfig.getPrivateKey());
                return appCertBundle.createKeyStore("server");
            }

            @Override
            public KeyStore getTrustStore() throws Exception {
                Certificate certPki = vaultTemplate.doWithVault(c -> c.execute("pki/ca", HttpMethod.GET, request -> {
                }, response -> {
                    try {
                        return CertificateFactory.getInstance("X.509").generateCertificate(response.getBody());
                    } catch (CertificateException e) {
                        throw new RuntimeException("Error reading CA certificate from vault.", e);
                    }
                }));
                KeyStore trustStore = KeyStore.getInstance("JKS");
                trustStore.load(null, null);
                trustStore.setCertificateEntry("vault_pki", certPki);
                return trustStore;
            }
        });
    }
}
