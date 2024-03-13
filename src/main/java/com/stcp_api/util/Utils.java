package com.stcp_api.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class Utils {

    private static final String STOP_HASH_CODE_ENDPOINT_1 = "https://www.stcp.pt/pt/viajar/horarios/?paragem=";
    private static final String STOP_HASH_CODE_ENDPOINT_2 = "&t=smsbus";

    public static String getStopHash(String stopCode) {
        String stopHashCode = null;

        try {
            Document doc = Jsoup.connect(STOP_HASH_CODE_ENDPOINT_1 + stopCode + STOP_HASH_CODE_ENDPOINT_2).get();

            Element table = doc.select("table").first();

            Element script = table.select("script").first();

            String[] scriptContent = script.html().split(",");
            stopHashCode = scriptContent[2].split("'")[1];

            return stopHashCode;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return stopHashCode;
    }


}
