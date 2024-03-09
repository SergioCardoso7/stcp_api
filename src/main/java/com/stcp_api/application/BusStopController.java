package com.stcp_api.application;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(path = "stcp_api/busstop")
public class BusStopController {


    @GetMapping("/scrape")
    public String scraperTest(@RequestParam String url) {
        try {
            Document doc = Jsoup.connect(url).get();

            System.out.println(doc.toString());

            return doc.html();

        } catch (IOException e) {

            e.printStackTrace();
            return "Did not work";
        }

    }

}
