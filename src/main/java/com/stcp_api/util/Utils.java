package com.stcp_api.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class Utils {

    private static final String STOP_HASH_CODE_ENDPOINT_1 = "https://www.stcp.pt/pt/viajar/horarios/?paragem=";
    private static final String STOP_HASH_CODE_ENDPOINT_2 = "&t=smsbus";

    private static final String ALL_BUS_LINES_ENDPOINT = "https://www.stcp.pt/pt/itinerarium/callservice.php?action=lineslist";

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

    public static String getBusLineInternalCode(String busLineCode) {

        try {
            Document doc = Jsoup.connect(ALL_BUS_LINES_ENDPOINT).get();

            String jsonString = doc.body().text();

            JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
            JsonArray records = jsonObject.getAsJsonArray("records");

            for (JsonElement record : records.asList()) {

                JsonObject innerJsonObject = record.getAsJsonObject();
                String lineCode = innerJsonObject.get("pubcode").getAsString();

                if (lineCode.equalsIgnoreCase(busLineCode)) {
                    return innerJsonObject.get("code").getAsString();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}
