package jvm.pablo.webcrawler.crawler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;

import jvm.pablo.webcrawler.exception.InvalidUrlFormatException;
import jvm.pablo.webcrawler.extractor.Extractor;
import jvm.pablo.webcrawler.extractor.ExtractorImpl;

@Component
public class Crawler {

    private Extractor extractor;

    public Crawler() {
    }

    @Autowired
    public Crawler(ExtractorImpl extractor) {
        this.extractor = extractor;
    }

    // TODO: 10/15/21 making the method work
    public void recursiveFindUrls(String url) {
        String htmlStringFormat = processUrl(url);
        List<String> list = processSubUrls(htmlStringFormat);

        List<List<String>> recursiveLists = list.stream()
                .map(this::processUrl)
                .map(this::processSubUrls)
                .collect(Collectors.toList());


    }

    public String processUrl(String url) {
        return null;
    }

    public List<String> processSubUrls(String url) {
        return extractor.extractUrls(url);
    }
}
