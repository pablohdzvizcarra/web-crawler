package jvm.pablo.webcrawler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jvm.pablo.webcrawler.model.UrlRequest;

@Controller
public class TemplateController {

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("request", new UrlRequest());
        model.addAttribute("exampleValue", "simple text");
        return "index";
    }

    @PostMapping("/crawler")
    public String findByUrl(@ModelAttribute UrlRequest data, Model model) {
        model.addAttribute("modelValue", "Tom Clancy's");
        model.addAttribute("request", new UrlRequest());
        return "index";
    }

}
