package jvm.pablo.webcrawler.crawler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import jvm.pablo.webcrawler.extractor.ExtractorImpl;
import jvm.pablo.webcrawler.extractor.ExtractorUrl;
import jvm.pablo.webcrawler.model.ModelResponseCrawlerComponent;

@Component
public class Crawler {
    private final ExtractorUrl extractor;
    private final SelectorUrl selectorUrl;
    private final ModelResponseCrawlerComponent responseCrawlerComponent;

    @Autowired
    public Crawler(
            ExtractorImpl extractor,
            SelectorUrl selectorUrl,
            ModelResponseCrawlerComponent responseCrawlerComponent
    ) {
        this.extractor = extractor;
        this.selectorUrl = selectorUrl;
        this.responseCrawlerComponent = responseCrawlerComponent;
    }

    public Map<Set<String>, Set<String>> recursiveFindUrls(String url) {
        return extractor.extractNestedUrls(url);
    }

    public Set<String> processUrl(String url) {
        return extractor.extractUrlsInsidePrimaryUrl(url);
    }

    public HashMap<String, HashMap<String, ?>> findUrlsWithStatistics(String url) {
        Set<String> foundUrls = extractor.extractUrlsInsidePrimaryUrl(url);

        return responseCrawlerComponent.selectedByExtension(foundUrls, url);
    }

}
