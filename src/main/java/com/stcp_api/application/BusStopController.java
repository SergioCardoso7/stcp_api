package com.stcp_api.application;

import com.stcp_api.domain.exceptions.BusStopNotFoundException;
import com.stcp_api.domain.model.ArrivingBus;
import com.stcp_api.domain.model.BusStop;
import com.stcp_api.domain.model.BusStopDTO;
import com.stcp_api.domain.services.BusStopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "stcp_api/busstop")
public class BusStopController {
    private final BusStopService busStopService;
    @Autowired
    public BusStopController(BusStopService service){
        this.busStopService = service;
    }
    @GetMapping("stopdata")
    public BusStopDTO getBusStopData(@RequestParam String stopCode) throws BusStopNotFoundException, IOException {
        return busStopService.getBusStopDataByBusCode(stopCode);
    }
    @GetMapping("stoprealtimes")
    public List<ArrivingBus> getIncomingBusses(@RequestParam String stopCode) throws BusStopNotFoundException, IOException {
        return busStopService.getIncomingBusses(stopCode);
    }
}
