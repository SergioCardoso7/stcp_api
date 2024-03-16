package com.stcp_api.domain.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.stcp_api.domain.exceptions.BusLineNotFoundException;
import com.stcp_api.domain.exceptions.BusStopNotFoundException;
import com.stcp_api.domain.model.BusLineDTO;
import com.stcp_api.domain.model.BusStopDTO;
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
    private static final String BUS_LINE_STOPS_ENDPOINT_1 = "https://www.stcp.pt/pt/itinerarium/callservice.php?action=linestops&lcode=";
    private static final String BUS_LINE_STOPS_ENDPOINT_2 = "&ldir=";
    BusStopService busStopService = new BusStopService();

    public List<BusLineDTO> getAllBusLines() throws IOException {

        List<BusLineDTO> allBuslines = new ArrayList<>();

        Document doc = Jsoup.connect(ALL_BUS_LINES_ENDPOINT).get();

        String jsonString = doc.body().text();

        JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();

        JsonArray records = jsonObject.getAsJsonArray("records");

        for (JsonElement record : records.asList()) {

            BusLineDTO busLineDTO = getBusLineDTO(record);

            allBuslines.add(busLineDTO);

        }

        return allBuslines;
    }


    public List<BusStopDTO> getLineBusStops(String lineCode, int directionCode) throws BusLineNotFoundException, IOException, BusStopNotFoundException {

        List<BusStopDTO> busStops = new ArrayList<>();

        lineCode = getBusLineInternalCode(lineCode);

        Document doc = Jsoup.connect(BUS_LINE_STOPS_ENDPOINT_1 + lineCode + BUS_LINE_STOPS_ENDPOINT_2 + directionCode).get();

        String busStopInformation = doc.body().text();

        JsonObject jsonObject = JsonParser.parseString(busStopInformation).getAsJsonObject();

        JsonArray records = jsonObject.getAsJsonArray("records");

        for (JsonElement record : records.asList()) {

            BusStopDTO busStopDTO = getBusStopDTO(record);

            busStops.add(busStopDTO);

        }

        return busStops;
    }

    private BusStopDTO getBusStopDTO(JsonElement record) throws BusStopNotFoundException, IOException {

        JsonObject innerJsonObject = record.getAsJsonObject();

        String busStopCode = innerJsonObject.get("code").getAsString();

        return busStopService.getBusStopDataByBusCode(busStopCode);

    }

    private BusLineDTO getBusLineDTO(JsonElement record) {

        JsonObject innerJsonObject = record.getAsJsonObject();
        String lineCode = innerJsonObject.get("pubcode").getAsString();
        String description = innerJsonObject.get("description").getAsString();

        String[] descriptionParts = description.split("-");
        String startStop = descriptionParts[1].trim();
        String endStop = descriptionParts[2].trim();

        LineDirection directionOne = new LineDirection(0, endStop);
        LineDirection directionTwo = new LineDirection(1, startStop);

        return new BusLineDTO(lineCode, directionOne, directionTwo);
    }

    public String getBusLineInternalCode(String busLineCode) throws BusLineNotFoundException, IOException {


        Document doc = Jsoup.connect(ALL_BUS_LINES_ENDPOINT).get();

        String jsonString = doc.body().text();

        JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
        JsonArray records = jsonObject.getAsJsonArray("records");

        for (JsonElement record : records.asList()) {

            JsonObject innerJsonObject = record.getAsJsonObject();
            String lineCode = innerJsonObject.get("pubcode").getAsString();

            if (lineCode.equalsIgnoreCase(busLineCode)) {
                return innerJsonObject.get("code").getAsString().trim();
            }
        }

        throw new BusLineNotFoundException(busLineCode);

    }
}
