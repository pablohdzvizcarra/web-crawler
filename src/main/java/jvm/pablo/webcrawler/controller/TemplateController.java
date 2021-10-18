package jvm.pablo.webcrawler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import jvm.pablo.webcrawler.model.UrlRequest;
import jvm.pablo.webcrawler.service.CrawlerService;

@Controller
@RequestMapping("/")
public class TemplateController {
    private final CrawlerService crawlerService;

    @Autowired
    public TemplateController(CrawlerService crawlerService) {
        this.crawlerService = crawlerService;
    }

    @RequestMapping
    public String index(Model model) {
        model.addAttribute("selected", false);
        model.addAttribute("request", new UrlRequest());
        model.addAttribute("listUrls", new ArrayList<>());

        return "index";
    }

    @PostMapping
    public String findByUrl(@ModelAttribute UrlRequest data, Model model) {
        Set<String> urls = crawlerService.findUrls(data.getUrl());
        model.addAttribute("selected", false);
        model.addAttribute("request", new UrlRequest());
        model.addAttribute("listUrls", urls);

        return "index";
    }

    @PostMapping("/selected")
    public String findBySelected(@ModelAttribute UrlRequest data, Model model) {
        Set<String> urls = crawlerService.findUrls(data.getUrl());
        HashMap<String, HashMap<String, ?>> selectedUrls =
                crawlerService.findWithStatistics(data.getUrl());


        model.addAttribute("selected", false);
        model.addAttribute("request", new UrlRequest());
        model.addAttribute("listUrls", urls);

        return "index";
    }

}
