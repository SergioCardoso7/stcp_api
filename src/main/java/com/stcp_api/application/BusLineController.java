package com.stcp_api.application;

import com.stcp_api.domain.model.BusLine;
import com.stcp_api.domain.services.BusLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "stcp_api/busline")
public class BusLineController {


    private final BusLineService busLineService;
    @Autowired
    public BusLineController(BusLineService service){
        this.busLineService = service;
    }

    @GetMapping("/getbuslines")
    public List<BusLine> getAllBusLines() {

        return busLineService.getAllBusLines();

    }
}
