package com.stcp_api.domain.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.stcp_api.domain.exceptions.BusStopNotFoundException;
import com.stcp_api.domain.model.ArrivingBus;
import com.stcp_api.domain.model.BusStopDTO;
import com.stcp_api.domain.model.stcpresponse.SearchBusStopModel;
import com.stcp_api.domain.utils.StcpSearchEndpoint.StcpSearchEndpointBuilder;
import com.stcp_api.mapper.ResponseMapper;
import io.github.cdimascio.dotenv.Dotenv;
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

    private final Dotenv dotenv = Dotenv.load();
    private final String UID = dotenv.get("UID");
    private final String REAL_TIME_BUS_ENDPOINT = dotenv.get("REAL_TIME_BUS_ENDPOINT_1");
    private final String REAL_TIME_BUS_ENDPOINT2 = dotenv.get("REAL_TIME_BUS_ENDPOINT_2");
    private final String BUS_STOP_DATA_ENDPOINT = "https://www.stcp.pt/pt/itinerarium/callservice.php?action=srchstoplines&stopname=";



    /**
     * This method returns the bus stop data by the bus stop code
     *
     * @param busStop the bus stop code
     * @return the bus stop data
     * @throws BusStopNotFoundException if the bus stop does not exist
     * @throws IOException              if there is an error in the connection
     */

    public BusStopDTO getBusStopDataByBusCode(String busStop) throws BusStopNotFoundException, IOException {

        JsonArray jsonArray = busStopJsonArray(busStop);

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
    }

    /**
     * This method returns the incoming buses to a bus stop
     *
     * @param stopCode the bus stop code
     * @return the incoming buses to a bus stop
     * @throws IOException              if there is an error in the connection
     * @throws BusStopNotFoundException if the bus stop does not exist
     */

    public List<ArrivingBus> getIncomingBusses(String stopCode) throws IOException, BusStopNotFoundException {

        if (!busStopExists(stopCode)) throw new BusStopNotFoundException(stopCode);

        Document doc = Jsoup.connect(REAL_TIME_BUS_ENDPOINT + UID + REAL_TIME_BUS_ENDPOINT2 + stopCode).get();
        return getIncomingBussesInfo(doc);
    }

    public List<BusStopDTO> getBusStopBySearchTerm(String searchTerm) throws IOException {
        Document doc = Jsoup.connect(prepareSearchByTermEndpoint(searchTerm)).get();
        return filterDocStops(doc);
    }

    private List<BusStopDTO> filterDocStops(Document doc) throws JsonProcessingException {
        String body = doc.body().text();
        String responseJson = JsonParser.parseString(body).getAsJsonObject().toString();
        ObjectMapper objectMapper = new ObjectMapper();
        SearchBusStopModel responseObject = objectMapper.readValue(responseJson, SearchBusStopModel.class);

        return responseObject.records().stream()
                .map(ResponseMapper.INSTANCE::responseStopRecordToBusStop)
                .toList();
    }

    public List<ArrivingBus> getIncomingBussesInfo(Document doc) {
        List<ArrivingBus> arrivingBuses = new ArrayList<>();
        Elements busElements = doc.select(".separa");

        for (Element busElement : busElements) {
            String busLineCode = busElement.select(".floatLeft.Linha1").text().trim();
            if (busLineCode.isBlank()) continue;

            String endBusStopName = busElement.select(".floatLeft.Linha2").text().trim();
            String minutesAndTime = busElement.select(".floatLeft.Linha4").text().trim();

            if (!minutesAndTime.contains(":")) {
                arrivingBuses.add(new ArrivingBus(busLineCode, endBusStopName, minutesAndTime));
            } else {
                String[] etaAndWaitingTimeParts = minutesAndTime.split("-");
                String waitingTime = etaAndWaitingTimeParts[1].trim();
                String[] hoursAndMinutes = etaAndWaitingTimeParts[0].split(":");
                int hours = Integer.parseInt(hoursAndMinutes[0].trim());
                int minutes = Integer.parseInt(hoursAndMinutes[1].trim());
                LocalTime estimatedTimeOfArrival = LocalTime.of(hours, minutes);

                arrivingBuses.add(new ArrivingBus(busLineCode, endBusStopName, estimatedTimeOfArrival, waitingTime));
            }
        }
        return arrivingBuses;
    }

    public JsonArray busStopJsonArray(String busStopCode) throws IOException {

        Document doc = Jsoup.connect(BUS_STOP_DATA_ENDPOINT + busStopCode).get();
        String info = doc.body().text();

        return JsonParser.parseString(info).getAsJsonArray();
    }

    public boolean busStopExists(String busStopCode) throws IOException{
        return !busStopJsonArray(busStopCode).isEmpty();
    }
    private String prepareSearchByTermEndpoint(String searchTerm) {
        String basePath = "https://www.stcp.pt/pt/itinerarium/callservice.php";
        String action = "srchlocbname";

        return new StcpSearchEndpointBuilder(basePath, action)
                .withQueryString(searchTerm)
                .withFltBus(true)
                .build().toString();
    }
}
