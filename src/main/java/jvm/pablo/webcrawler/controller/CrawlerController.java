package jvm.pablo.webcrawler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import jvm.pablo.webcrawler.service.CrawlerService;

@RestController
@RequestMapping("/api/crawler")
public class CrawlerController {
    private final CrawlerService crawlerService;

    @Autowired
    public CrawlerController(CrawlerService crawlerService) {
        this.crawlerService = crawlerService;
    }


    @GetMapping(params = "url")
    public ResponseEntity<Set<String>> findUrls(@RequestParam String url) {
        Set<String> urls = crawlerService.findUrls(url);
        return ResponseEntity.ok(urls);
    }

    @GetMapping(value = "/recursive", params = "url")
    public ResponseEntity<?> findRecursive(@RequestParam String url) {
        crawlerService.findRecursive(url);

        return ResponseEntity.ok("ok");
    }

}
