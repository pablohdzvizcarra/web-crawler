package jvm.pablo.webcrawler.crawler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jvm.pablo.webcrawler.extractor.ExtractorUrl;
import jvm.pablo.webcrawler.extractor.ExtractorImpl;

@Component
public class Crawler {

    private ExtractorUrl extractor;
    private SelectorUrl selectorUrl;

    public Crawler() {
    }

    @Autowired
    public Crawler(ExtractorImpl extractor, SelectorUrl selectorUrl) {
        this.extractor = extractor;
        this.selectorUrl = selectorUrl;
    }

    public Map<Set<String>, Set<String>> recursiveFindUrls(String url) {
        return extractor.extractNestedUrls(url);
    }

    public Set<String> processUrl(String url) {
        return extractor.extractUrlsInsidePrimaryUrl(url);
    }

    public HashMap<String, Collection<?>> findUrlsWithStatistics(String url) {
        HashMap<String, Collection<String>> mapUrls = new HashMap<>();
        HashMap<String, Integer> mapCount = new HashMap<>();
        Set<String> foundUrls = extractor.extractUrlsInsidePrimaryUrl(url);

        List<String> filesJs = selectorUrl.findWithExtensionFile(foundUrls, ".js");
        List<String> filesCss = selectorUrl.findWithExtensionFile(foundUrls, ".css");
        List<String> filesPng = selectorUrl.findWithExtensionFile(foundUrls, ".png");
        List<String> filesJson = selectorUrl.findWithExtensionFile(foundUrls, ".json");

        mapUrls.put("Find Urls: ", foundUrls);
        mapUrls.put("Find urls with extension JS", filesJs);
        mapUrls.put("Find urls with extension CSS", filesCss);
        mapUrls.put("Find urls with extension PNG", filesPng);
        mapUrls.put("Find urls with extension JSON", filesJson);

        mapCount.put("Number of urls found: ", foundUrls.size());
        mapCount.put("Number of urls with extension JS found: ", filesJs.size());
        mapCount.put("Number of urls with extension CSS found: ", filesCss.size());
        mapCount.put("Number of urls with extension PNG found: ", filesPng.size());
        mapCount.put("Number of urls with extension JSON found: ", filesJson.size());

        HashMap<String, Collection<?>> result = new HashMap<>();
        result.put("urls", Collections.singleton(mapUrls));
        result.put("count", Collections.singleton(mapCount));
        return result;
    }

}
