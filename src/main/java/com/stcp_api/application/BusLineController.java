package com.stcp_api.application;

import com.stcp_api.domain.exceptions.BusLineNotFoundException;
import com.stcp_api.domain.exceptions.BusStopNotFoundException;
import com.stcp_api.domain.exceptions.InvalidLineDirectionException;
import com.stcp_api.domain.model.BusLineDTO;
import com.stcp_api.domain.model.BusStopDTO;
import com.stcp_api.domain.services.BusLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * Controller for the bus line data.
 */

@RestController
@RequestMapping(path = "stcp_api/busline")
public class BusLineController {

    private final BusLineService busLineService;
    @Autowired
    public BusLineController(BusLineService service) {
        this.busLineService = service;
    }

    /**
     * Get all the bus lines.
     * @return the bus lines.
     * @throws IOException if there is an error in the connection.
     */
    @GetMapping("/getbuslines")
    public List<BusLineDTO> getAllBusLines() throws IOException {
        return busLineService.getAllBusLines();
    }
    /**
     * Get the bus stops of a line.
     * @param lineCode the line code.
     * @param directionCode the direction code.
     * @return the bus stops of the line.
     * @throws IOException if there is an error in the connection.
     * @throws BusLineNotFoundException if the line code is invalid.
     * @throws BusStopNotFoundException if s bus stop is invalid.
     */

    @GetMapping("/linestops")
    public List<BusStopDTO> getStopsOfLine(@RequestParam String lineCode,
                                           @RequestParam int directionCode) throws IOException, BusLineNotFoundException, BusStopNotFoundException, InvalidLineDirectionException {
        return busLineService.getLineBusStops(lineCode, directionCode);
    }
}
