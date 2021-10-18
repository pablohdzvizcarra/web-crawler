package jvm.pablo.webcrawler.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import jvm.pablo.webcrawler.crawler.SelectorUrl;

@Component
public class ModelResponseCrawlerComponent {
    private final SelectorUrl selectorUrl;

    @Autowired
    public ModelResponseCrawlerComponent(SelectorUrl selectorUrl) {
        this.selectorUrl = selectorUrl;
    }

    public HashMap<String, HashMap<String, ?>> selectedByExtension(
            Collection<String> urlsList, String primaryUrl) {
        HashMap<String, Collection<String>> separateUrls = createMapUrls(urlsList, primaryUrl);
        HashMap<String, Integer> urlsCount = createUrlsCountResponse(separateUrls);

        HashMap<String, HashMap<String, ?>> result = new HashMap<>();
        result.put("urls", separateUrls);
        result.put("count", urlsCount);

        return result;
    }

    private HashMap<String, Collection<String>> createMapUrls(Collection<String> urls, String url) {
        HashMap<String, Collection<String>> hashMap = new HashMap<>();
        List<String> filesJs = selectorUrl.findWithExtensionFile(urls, ".js");
        List<String> filesCss = selectorUrl.findWithExtensionFile(urls, ".css");
        List<String> filesPng = selectorUrl.findWithExtensionFile(urls, ".png");
        List<String> filesJson = selectorUrl.findWithExtensionFile(urls, ".json");
        List<String> anotherWebPages = selectorUrl.findReferencesToAnotherPages(url, urls);

        hashMap.put("allUrls", urls);
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

        hashMap.put("allUrls", selectedUrls.get("allUrls").size());
        hashMap.put("javascript", selectedUrls.get("javascript").size());
        hashMap.put("css", selectedUrls.get("css").size());
        hashMap.put("png", selectedUrls.get("png").size());
        hashMap.put("json", selectedUrls.get("json").size());
        hashMap.put("referencesOtherUrls", selectedUrls.get("othersPages").size());

        return hashMap;
    }
}
