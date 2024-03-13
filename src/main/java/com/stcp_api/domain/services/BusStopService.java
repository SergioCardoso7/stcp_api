package com.stcp_api.domain.services;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class BusStopService {

    private static final String STOP_HASH_CODE_ENDPOINT_1 = "https://www.stcp.pt/pt/viajar/horarios/?paragem=";
    private static final String STOP_HASH_CODE_ENDPOINT_2 = "&t=smsbus";

    public BusStopService() {

    }
}
