package com.stcp_api.domain.services;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.stcp_api.domain.exceptions.BusStopNotFoundException;
import com.stcp_api.domain.model.ArrivingBus;
import com.stcp_api.domain.model.BusStopDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BusStopService {

    private final String BUS_STOP_DATA_ENDPOINT = "https://www.stcp.pt/pt/itinerarium/callservice.php?action=srchstoplines&stopname=";

    private final String REAL_TIME_BUS_ENDPOINT = "https://www.stcp.pt/pt/widget/post.php?uid=";
    private final String REAL_TIME_BUS_ENDPOINT2 = "&paragem=";
    private final String UID = "d72242190a22274321cacf9eadc7ec5f";

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
    public BusStopDTO getBusStopDataByBusCode(String busStop) {

        try {

            Document doc = Jsoup.connect(BUS_STOP_DATA_ENDPOINT + busStop).get();

            String info = doc.body().text();

            JsonArray jsonArray = JsonParser.parseString(info).getAsJsonArray();

            if (jsonArray.isEmpty()) throw new BusStopNotFoundException(busStop);

            JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();

            String busStopCode = jsonObject.get("code").getAsString();
            String busStopName = jsonObject.get("name").getAsString();
            String busStopZone = jsonObject.get("zone").getAsString();

            String geomdesc = jsonObject.get("geomdesc").getAsString();

            JsonObject geomdescObject = JsonParser.parseString(geomdesc).getAsJsonObject();

            double longitude = geomdescObject.getAsJsonArray("coordinates").get(0).getAsDouble();
            double latitude = geomdescObject.getAsJsonArray("coordinates").get(1).getAsDouble();

            return new BusStopDTO(busStopCode, busStopName, busStopZone, new Point2D.Double(longitude, latitude));

        //TODO: More robust error handling

        } catch (IOException e) {

            e.printStackTrace();
            return null;

        }catch (BusStopNotFoundException e){
            e.printStackTrace();
            return null;
        }

    }

    public List<ArrivingBus> getIncomingBusses(String stopCode) {

        try {

            Document doc = Jsoup.connect(REAL_TIME_BUS_ENDPOINT + UID + REAL_TIME_BUS_ENDPOINT2 + stopCode).get();

            return getArrivingBussesInfo(doc);

        } catch (IOException e) {

            e.printStackTrace();
            return null;
        }

    }

    public List<ArrivingBus> getArrivingBussesInfo(Document doc) {

        List<ArrivingBus> arrivingBuses = new ArrayList<>();

        Elements busElements = doc.select(".separa");

        for (Element busElement : busElements) {

            String busLineCode = busElement.select(".floatLeft.Linha1").text().trim();
            if (busLineCode.isBlank()) continue;

            String endBusStopName = busElement.select(".floatLeft.Linha2").text().trim();
            String minutesAndTime = busElement.select(".floatLeft.Linha4").text().trim();

            if(minutesAndTime.equalsIgnoreCase("a passar - ")){

                arrivingBuses.add(new ArrivingBus(busLineCode,endBusStopName,minutesAndTime));

            }else {

                String[] timeAndMinutesParts = minutesAndTime.split("-");

                String waitingTime = timeAndMinutesParts[1].trim();

                String[] hoursAndMinutes = timeAndMinutesParts[0].split(":");

                int hours = Integer.parseInt(hoursAndMinutes[0].trim());
                int minutes = Integer.parseInt(hoursAndMinutes[1].trim());

                LocalTime estimatedTimeOfArrival = LocalTime.of(hours, minutes);

                arrivingBuses.add(new ArrivingBus(busLineCode, endBusStopName, estimatedTimeOfArrival, waitingTime));
            }

        }

        return arrivingBuses;
    }

    //should return a list of stops with that name
    /*
    public JsonObject getBusStopDataByName(String name) {


    }

     */


}
