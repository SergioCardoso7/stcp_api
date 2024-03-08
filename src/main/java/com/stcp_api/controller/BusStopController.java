package com.stcp_api.controller;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class BusStopController {


    @GetMapping("/scraper")
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
