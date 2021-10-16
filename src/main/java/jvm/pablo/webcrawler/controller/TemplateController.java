package jvm.pablo.webcrawler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

import jvm.pablo.webcrawler.model.UrlRequest;
import jvm.pablo.webcrawler.service.CrawlerService;

@Controller
public class TemplateController {
    private final CrawlerService crawlerService;

    @Autowired
    public TemplateController(CrawlerService crawlerService) {
        this.crawlerService = crawlerService;
    }

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("request", new UrlRequest());

        return "index";
    }

    @PostMapping("/crawler")
    public String findByUrl(@ModelAttribute UrlRequest data, Model model) {
        Set<String> urls = crawlerService.findUrls(data.getUrl());

        model.addAttribute("request", new UrlRequest());
        model.addAttribute("listUrls", urls);

        return "withUrls";
    }

}
