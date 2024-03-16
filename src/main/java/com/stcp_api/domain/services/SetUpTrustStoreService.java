package com.stcp_api.domain.services;

import javax.net.ssl.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class SetUpTrustStoreService {

    private static final String TRUSTSTORE_FILE = "stcp_truststore.jks";
    private static final String TRUSTSTORE_PASSWORD = "changeit";

    public SetUpTrustStoreService(String url) {
        try {

            initializeCustomTrustStore(url);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeCustomTrustStore(String url) throws Exception {

        KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());

        File truststoreFile = new File(TRUSTSTORE_FILE);

        try (FileInputStream in = new FileInputStream(TRUSTSTORE_FILE)) {
            trustStore.load(in, TRUSTSTORE_PASSWORD.toCharArray());
        } catch (Exception ex) {
            trustStore.load(null, TRUSTSTORE_PASSWORD.toCharArray());
        }

        Certificate[] certificates = getWebsiteCertificates(url);

        if (certificates != null) {
            for (int i = 0; i < certificates.length; i++) {
                Certificate cert = certificates[i];
                trustStore.setCertificateEntry("cert" + i, cert);
            }
        }

        try (FileOutputStream out = new FileOutputStream(TRUSTSTORE_FILE)) {
            trustStore.store(out, TRUSTSTORE_PASSWORD.toCharArray());
        }

        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(trustStore);
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustManagerFactory.getTrustManagers(), null);
        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
    }


    public Certificate[] getWebsiteCertificates(String url) {

        try {

            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                    }
            };

            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            URL testUrl = new URL(url);
            HttpsURLConnection connection = (HttpsURLConnection) testUrl.openConnection();
            connection.connect();

            return connection.getServerCertificates();

        } catch (NoSuchAlgorithmException | KeyManagementException | IOException e) {

            e.printStackTrace();
            return null;

        }
    }
}
