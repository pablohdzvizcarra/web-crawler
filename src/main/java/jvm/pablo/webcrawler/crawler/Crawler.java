package jvm.pablo.webcrawler.crawler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

import jvm.pablo.webcrawler.extractor.ExtractorUrl;
import jvm.pablo.webcrawler.extractor.ExtractorImpl;

@Component
public class Crawler {

    private ExtractorUrl extractor;

    public Crawler() {
    }

    @Autowired
    public Crawler(ExtractorImpl extractor) {
        this.extractor = extractor;
    }

    public Map<Set<String>, Set<String>> recursiveFindUrls(String url) {
        return extractor.extractNestedUrls(url);
    }

    public Set<String> processUrl(String url) {
        return extractor.extractUrlsInsidePrimaryUrl(url);
    }

}
