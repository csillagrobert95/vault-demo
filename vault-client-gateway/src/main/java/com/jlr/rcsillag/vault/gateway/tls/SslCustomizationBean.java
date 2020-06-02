package com.jlr.rcsillag.vault.gateway.tls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.SslStoreProvider;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultCertificateRequest;
import org.springframework.vault.support.VaultCertificateResponse;

import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Arrays;

/**
 * Created by Robi on 5/29/2020.
 */
@Component
public class SslCustomizationBean implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {
    private VaultTemplate vaultTemplate;

    public SslCustomizationBean(@Autowired VaultTemplate vaultTemplate) {
        this.vaultTemplate = vaultTemplate;
    }

    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {
        factory.setSslStoreProvider(new SslStoreProvider() {
            @Override
            public KeyStore getKeyStore() {
                VaultCertificateResponse vaultResponse = vaultTemplate.opsForPki("pki_int")
                        .issueCertificate("server", VaultCertificateRequest.builder()
                                .commonName("localhost")
                                .ipSubjectAltNames(Arrays.asList("10.128.0.4"))
                                .build());
                return vaultResponse.getData().createKeyStore("server");
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
                Certificate certPkiInt = vaultTemplate.doWithVault(c -> c.execute("pki_int/ca", HttpMethod.GET, request -> {
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
                trustStore.setCertificateEntry("vault_pki_int", certPkiInt);
                return trustStore;
            }
        });
    }
}
