package jvm.pablo.webcrawler.crawler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jvm.pablo.webcrawler.extractor.ExtractorUrl;
import jvm.pablo.webcrawler.extractor.ExtractorImpl;

@Component
public class Crawler {
    private final ExtractorUrl extractor;
    private final SelectorUrl selectorUrl;

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

    // TODO: 10/18/21 refactor this
    public HashMap<String, HashMap<String, ?>> findUrlsWithStatistics(String url) {
        HashMap<String, Collection<String>> mapUrls = new HashMap<>();
        HashMap<String, Integer> mapCount = new HashMap<>();
        Set<String> foundUrls = extractor.extractUrlsInsidePrimaryUrl(url);

        List<String> filesJs = selectorUrl.findWithExtensionFile(foundUrls, ".js");
        List<String> filesCss = selectorUrl.findWithExtensionFile(foundUrls, ".css");
        List<String> filesPng = selectorUrl.findWithExtensionFile(foundUrls, ".png");
        List<String> filesJson = selectorUrl.findWithExtensionFile(foundUrls, ".json");

        mapUrls.put("urls", foundUrls);
        mapUrls.put("javascript", filesJs);
        mapUrls.put("css", filesCss);
        mapUrls.put("png", filesPng);
        mapUrls.put("json", filesJson);

        mapCount.put("urls", foundUrls.size());
        mapCount.put("javascript", filesJs.size());
        mapCount.put("css", filesCss.size());
        mapCount.put("png", filesPng.size());
        mapCount.put("json", filesJson.size());

        HashMap<String, HashMap<String, ?>> result = new HashMap<>();
        result.put("urls", mapUrls);
        result.put("count", mapCount);
        return result;
    }

}
