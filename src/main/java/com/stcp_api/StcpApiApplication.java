package com.stcp_api;

import com.stcp_api.models.services.SetUpTrustStoreService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StcpApiApplication {

    private static final String STCP_URL = "https://www.stcp.pt/pt/viajar/";

    public static void main(String[] args) {


        SetUpTrustStoreService websiteCertificateService = new SetUpTrustStoreService(STCP_URL);

        SpringApplication.run(StcpApiApplication.class, args);
    }

}
