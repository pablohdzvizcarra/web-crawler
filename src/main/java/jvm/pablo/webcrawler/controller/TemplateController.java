package jvm.pablo.webcrawler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import jvm.pablo.webcrawler.model.UrlRequest;
import jvm.pablo.webcrawler.service.CrawlerService;

import static jvm.pablo.webcrawler.model.ModelResponseCrawlerComponent.ALL_URLS;
import static jvm.pablo.webcrawler.model.ModelResponseCrawlerComponent.CSS_URL;
import static jvm.pablo.webcrawler.model.ModelResponseCrawlerComponent.JSON_URL;
import static jvm.pablo.webcrawler.model.ModelResponseCrawlerComponent.JS_URL;
import static jvm.pablo.webcrawler.model.ModelResponseCrawlerComponent.KEY_URLS;
import static jvm.pablo.webcrawler.model.ModelResponseCrawlerComponent.OTHERS_URLS;
import static jvm.pablo.webcrawler.model.ModelResponseCrawlerComponent.PNG_URL;

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
        model.addAttribute("request", new UrlRequest());
        model.addAttribute("listUrls", new ArrayList<>());
        model.addAttribute("selectedUrl", false);

        return "index";
    }

    @PostMapping
    public String findByUrl(@ModelAttribute UrlRequest data, Model model) {
        HashMap<String, HashMap<String, ?>> selectedUrls =
                crawlerService.findWithStatistics(data.getUrl());
        @SuppressWarnings("unchecked")
        HashMap<String, Collection<String>> urlsHash =
                (HashMap<String, Collection<String>>) selectedUrls.get(KEY_URLS);

        Collection<String> urlsList = urlsHash.get(ALL_URLS);
        Collection<String> othersList = urlsHash.get(OTHERS_URLS);
        Collection<String> jsList = urlsHash.get(JS_URL);
        Collection<String> cssList = urlsHash.get(CSS_URL);
        Collection<String> jsonList = urlsHash.get(JSON_URL);
        Collection<String> pngList = urlsHash.get(PNG_URL);

        model.addAttribute("request", new UrlRequest());
        model.addAttribute("listUrls", urlsList);
        model.addAttribute("listAnotherUrl", othersList);
        model.addAttribute("listJS", jsList);
        model.addAttribute("listCSS", cssList);
        model.addAttribute("listPNG", pngList);
        model.addAttribute("listJSON", jsonList);
        model.addAttribute("selectedUrl", true);

        return "index";
    }

    @GetMapping("/selected")
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
