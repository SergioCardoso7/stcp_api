package com.stcp_api;

import com.stcp_api.domain.services.SetUpTrustStoreService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class StcpApiApplication {

    private static final String STCP_URL = "https://www.stcp.pt";

    public static void main(String[] args) {

        SetUpTrustStoreService websiteCertificateService = new SetUpTrustStoreService(STCP_URL);

        SpringApplication.run(StcpApiApplication.class, args);
    }

}
