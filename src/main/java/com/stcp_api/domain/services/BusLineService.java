package com.stcp_api.domain.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.stcp_api.domain.model.*;
import com.stcp_api.util.Utils;
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

    // {"accessibility": 1, "code": "200", "pubcode": "200", "description": "200 - BOLHÃO-CAST. QUEIJO"}

    BusStopService busStopService = new BusStopService();

    public List<BusLineDTO> getAllBusLines() {

        List<BusLineDTO> allBuslines = new ArrayList<>();

        try {

            Document doc = Jsoup.connect(ALL_BUS_LINES_ENDPOINT).get();

            String jsonString = doc.body().text();

            JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();

            JsonArray records = jsonObject.getAsJsonArray("records");

            for (JsonElement record : records.asList()) {

                BusLineDTO busLineDTO = getBusLineDTO(record);

                allBuslines.add(busLineDTO);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return allBuslines;
    }


    public List<BusStopDTO> getLineBusStops(String lineCode, int directionCode) {
        List<BusStopDTO> busStops = new ArrayList<>();

        try {
            Document doc = Jsoup.connect(BUS_LINE_STOPS_ENDPOINT_1 + lineCode + BUS_LINE_STOPS_ENDPOINT_2 + directionCode).get();

            String info = doc.body().text();

            JsonObject jsonObject = JsonParser.parseString(info).getAsJsonObject();

            JsonArray records = jsonObject.getAsJsonArray("records");

            for (JsonElement record : records.asList()) {

                BusStopDTO busStopDTO = getBusStopDTO(record);

                busStops.add(busStopDTO);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return busStops;
    }

    private BusStopDTO getBusStopDTO(JsonElement record) {

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

        BusLineDTO busLineDTO = new BusLineDTO(lineCode, directionOne, directionTwo);
        return busLineDTO;
    }
    /*

    {"sort": null, "recordsReturned": 15, "totalRecords": 15, "records": [
        {"zone": "PRT3", "code": "AREI2", "name": "AREIAS", "address": "R. AREIAS", "sequence": 1},
        {"zone": "PRT3", "code": "JD4", "name": "JOÃO DE DEUS", "address": "R. AREIAS", "sequence": 2},
        {"zone": "PRT3", "code": "PNG4", "name": "PÊGO NEGRO", "address": "R. AREIAS", "sequence": 3},
        {"zone": "PRT3", "code": "TVA2", "name": "TV. ALDEIA", "address": "R. AREIAS", "sequence": 4},
        {"zone": "PRT3", "code": "AZV", "name": "AZEVEDO", "address": "R.AZEVÊDO", "sequence": 5},
        {"zone": "PRT3", "code": "LGT2", "name": "LAGARTEIRO", "address": "R.AZEVÊDO", "sequence": 6},
        {"zone": "PRT3", "code": "RAZ", "name": "R. AZEVEDO", "address": "R.AZEVÊDO", "sequence": 7},
        {"zone": "PRT3", "code": "BNJ1", "name": "BONJÓIA", "address": "ESTR.CIRCUNVALAÇÃO", "sequence": 8},
        {"zone": "PRT3", "code": "ICAM2", "name": "IGREJA DE CAMPANHÃ", "address": "R.FALCÃO", "sequence": 9},
        {"zone": "PRT3", "code": "MCAM2", "name": "MONTE CAMPANHÃ", "address": "R.FALCÃO", "sequence": 10},
        {"zone": "PRT3", "code": "BFAL2", "name": "BR. FALCÃO", "address": "R.FALCÃO", "sequence": 11},
        {"zone": "PRT3", "code": "CHOL2", "name": "CHAVES OLIVEIRA", "address": "R.S.ROQUE LAMEIRA", "sequence": 12},
        {"zone": "PRT3", "code": "CRJ2", "name": "CORUJEIRA", "address": "R.S.ROQUE LAMEIRA", "sequence": 13},
        {"zone": "PRT1", "code": "EDRG2", "name": "ESTÁDIO DO DRAGÃO", "address": "ALAM.DAS ANTAS", "sequence": 14},
        {"zone": "PRT1", "code": "AANT5", "name": "ALAMEDA DAS ANTAS", "address": "VIA FUTEBOL CLUBE DO PORTO", "sequence": 15}
        ],
        "startIndex": 0,
        "dir": "asc"}

     */


}
