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

    public HashMap<String, HashMap<String, ?>> findUrlsWithStatistics(String url) {
        Set<String> foundUrls = extractor.extractUrlsInsidePrimaryUrl(url);
        HashMap<String, Collection<String>> selectedByExtensionUrls = createMapUrls(foundUrls, url);
        HashMap<String, Integer> urlsCountResponse = createUrlsCountResponse(selectedByExtensionUrls);

        HashMap<String, HashMap<String, ?>> result = new HashMap<>();
        result.put("urls", selectedByExtensionUrls);
        result.put("count", urlsCountResponse);
        return result;
    }

    private HashMap<String, Collection<String>> createMapUrls(Collection<String> urls, String url) {
        HashMap<String, Collection<String>> hashMap = new HashMap<>();
        List<String> filesJs = selectorUrl.findWithExtensionFile(urls, ".js");
        List<String> filesCss = selectorUrl.findWithExtensionFile(urls, ".css");
        List<String> filesPng = selectorUrl.findWithExtensionFile(urls, ".png");
        List<String> filesJson = selectorUrl.findWithExtensionFile(urls, ".json");
        List<String> anotherWebPages = selectorUrl.findReferencesToAnotherPages(url, urls);

        hashMap.put("urls", urls);
        hashMap.put("othersPages", anotherWebPages);
        hashMap.put("javascript", filesJs);
        hashMap.put("css", filesCss);
        hashMap.put("png", filesPng);
        hashMap.put("json", filesJson);

        return hashMap;
    }

    private HashMap<String, Integer> createUrlsCountResponse(
            HashMap<String, Collection<String>> selectedUrls) {
        HashMap<String, Integer> hashMap = new HashMap<>();

        hashMap.put("urls", selectedUrls.get("urls").size());
        hashMap.put("javascript", selectedUrls.get("javascript").size());
        hashMap.put("css", selectedUrls.get("css").size());
        hashMap.put("png", selectedUrls.get("png").size());
        hashMap.put("json", selectedUrls.get("json").size());
        hashMap.put("referencesOtherUrls", selectedUrls.get("othersPages").size());

        return hashMap;
    }

}
