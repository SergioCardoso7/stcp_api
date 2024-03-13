package com.stcp_api.application;

import com.stcp_api.domain.model.BusStopDTO;
import com.stcp_api.domain.services.BusStopService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(path = "stcp_api/busstop")
public class BusStopController {

    private final BusStopService busStopService;
    @Autowired
    public BusStopController(BusStopService service){
        this.busStopService = service;
    }

    @GetMapping("stopdata{stopCode}")
    public BusStopDTO getBusStopData(@RequestParam String stopCode){
        return busStopService.getBusStopDataByBusCode(stopCode);
    }

}
