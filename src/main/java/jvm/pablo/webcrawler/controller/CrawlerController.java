package jvm.pablo.webcrawler.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/crawler")
public class CrawlerController {

    @GetMapping(params = "url")
    public ResponseEntity<Void> findUrls(@RequestParam String url) {


        return ResponseEntity.accepted().build();
    }
}
