package com.stcp_api.domain.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.stcp_api.domain.model.BusLine;
import com.stcp_api.domain.model.BusStop;
import com.stcp_api.domain.model.LineDirection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BusLineService {

    private static final String ALL_BUS_LINES_ENDPOINT = "https://www.stcp.pt/pt/itinerarium/callservice.php?action=lineslist";

   // {"accessibility": 1, "code": "200", "pubcode": "200", "description": "200 - BOLHÃO-CAST. QUEIJO"}

    public List<BusLine> getAllBusLines(){

        List<BusLine> allBuslines = new ArrayList<>();

        try {

            Document doc = Jsoup.connect(ALL_BUS_LINES_ENDPOINT).get();

            String jsonString = doc.body().text();

            JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();

            JsonArray records = jsonObject.getAsJsonArray("records");

            for(JsonElement record : records.asList()){

                JsonObject innerJsonObject = record.getAsJsonObject();
                String lineCode = innerJsonObject.get("code").getAsString();
                String description = innerJsonObject.get("description").getAsString();

                String[] descriptionParts = description.split("-");
                String startStop = descriptionParts[1].trim();
                String endStop = descriptionParts[2].trim();

                LineDirection directionOne = new LineDirection(0,endStop);
                LineDirection directionTwo = new LineDirection(1,startStop);

                List<BusStop> busStopsOfLine = getLineBusStops(lineCode);

                BusLine busLineOne = new BusLine(lineCode, directionOne, busStopsOfLine);
                BusLine busLineTwo = new BusLine(lineCode, directionTwo, busStopsOfLine);

                allBuslines.add(busLineOne);
                allBuslines.add(busLineTwo);
            }

        }catch (IOException e){
            e.printStackTrace();
        }

        return allBuslines;
    }

    public List<BusStop> getLineBusStops(String lineCode){
        List<BusStop> busStops = new ArrayList<>();



        return busStops;
    }



}
