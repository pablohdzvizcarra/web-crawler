package jvm.pablo.webcrawler.crawler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    }

    public Set<String> processUrl(String url) {
        return extractor.extractUrlsInsideHtmlString(url);
    }

    public List<String> processSubUrls(String url) {
        return extractor.extractUrls(url);
    }
}
