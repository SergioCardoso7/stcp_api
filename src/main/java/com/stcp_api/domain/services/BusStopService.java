package com.stcp_api.domain.services;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.stcp_api.domain.model.BusStopDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.awt.geom.Point2D;
import java.io.IOException;

@Service
public class BusStopService {

    private final String BUS_STOP_DATA_ENDPOINT = "https://www.stcp.pt/pt/itinerarium/callservice.php?action=srchstoplines&stopname=";

    //[{"code": "PCT3", "name": "PICOUTOS", "zone": "MAI1", "lines": [{"accessibility": 2, "code": "506", "pubcode": "506", "dir": 1, "description": "HOSPITAL S.JOÃO"}], "geomdesc": "{\"type\":\"Point\",\"coordinates\":[-8.6233280838764,41.191419131449692]}", "mode": 1, "address": "R.5 OUTUBRO"}]
    /*
    [{"code": "IPO4", "name": "IPO (CIRCUNVAL.)", "zone": "PRT3",
            "lines": [
        {"accessibility": 1, "code": "205", "pubcode": "205", "dir": 1, "description": "CAMPANHÃ"},
        {"accessibility": 1, "code": "300", "pubcode": "300", "dir": 0, "description": "CIRC.HOSPITAL DE S.JOÃO-ALIADOS"},
        {"accessibility": 1, "code": "305", "pubcode": "305", "dir": 0, "description": "HOSPITAL DE S.JOÃO"},
        {"accessibility": 2, "code": "505", "pubcode": "505", "dir": 1, "description": "HOSPITAL S. JOÃO"},
        {"accessibility": 2, "code": "603", "pubcode": "603", "dir": 0, "description": "MAIA"},
        {"accessibility": 1, "code": "704", "pubcode": "704", "dir": 0, "description": "CODICEIRA"},
        {"accessibility": 2, "code": "705", "pubcode": "705", "dir": 1, "description": "HOSPITAL S. JOÃO"},
        {"accessibility": 2, "code": "804", "pubcode": "804", "dir": 1, "description": "HOSPITAL S.JOÃO"},
        {"accessibility": 2, "code": "9M", "pubcode": "9M", "dir": 0, "description": "GONDOMAR (VIA H. S. João)"},
        {"accessibility": 2, "code": "11M", "pubcode": "11M", "dir": 1, "description": "HOSP. S. JOÃO"}],
        "geomdesc": "{\"type\":\"Point\",\"coordinates\":[-8.604341550114464,41.183941881186499]}",
        "mode": 1,
        "address": "ESTR.CIRCUNVALAÇÃO"}]

     */
    public BusStopDTO getBusStopDataByBusCode(String busCode) {

        try {

            Document doc = Jsoup.connect(BUS_STOP_DATA_ENDPOINT + busCode).get();

            String info = doc.body().text();

            JsonArray jsonArray = JsonParser.parseString(info).getAsJsonArray();

            JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();

            String busStopCode = jsonObject.get("code").getAsString();
            String busStopName = jsonObject.get("name").getAsString();
            String busStopZone = jsonObject.get("zone").getAsString();

            String geomdesc = jsonObject.get("geomdesc").getAsString();

            JsonObject geomdescObject = JsonParser.parseString(geomdesc).getAsJsonObject();

            double longitude = geomdescObject.getAsJsonArray("coordinates").get(0).getAsDouble();
            double latitude = geomdescObject.getAsJsonArray("coordinates").get(1).getAsDouble();

            return new BusStopDTO(busStopCode, busStopName, busStopZone, new Point2D.Double(longitude, latitude));

        } catch (IOException e) {

            e.printStackTrace();
            return null;

        }

    }
    //should return a list of stops with that name
    /*
    public JsonObject getBusStopDataByName(String name) {


    }

     */


}
