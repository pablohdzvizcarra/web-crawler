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
    public static final String JS_URL = "javascriptUrls";
    public static final String ALL_URLS = "allUrls";
    public static final String CSS_URL = "cssUrls";
    public static final String JSON_URL = "jsonUrls";
    public static final String PNG_URL = "pngUrls";
    public static final String OTHERS_URLS = "othersUrls";
    public static final String CSS_NUMBER = "cssNumber";
    public static final String JS_NUMBER = "jsNumber";
    public static final String PNG_NUMBER = "pngNumber";
    public static final String JSON_NUMBER = "jsonNumber";
    public static final String ALL_URLS_NUMBER = "allUrlsNumber";
    public static final String OTHERS_URLS_NUMBER = "othersUrlsNumber";
    public static final String KEY_URLS = "urls";
    public static final String KEY_QUANTITY = "quantity";

    @Autowired
    public ModelResponseCrawlerComponent(SelectorUrl selectorUrl) {
        this.selectorUrl = selectorUrl;
    }

    public HashMap<String, HashMap<String, ?>> selectedByExtension(
            Collection<SafeUrl> urlsList, String primaryUrl) {
        HashMap<String, Collection<SafeUrl>> separateUrls = createMapUrls(urlsList, primaryUrl);
        HashMap<String, Integer> urlsCount = createUrlsCountResponse(separateUrls);

        HashMap<String, HashMap<String, ?>> result = new HashMap<>();
        result.put(KEY_URLS, separateUrls);
        result.put(KEY_QUANTITY, urlsCount);

        return result;
    }

    private HashMap<String, Collection<SafeUrl>> createMapUrls(Collection<SafeUrl> urls, String url) {
        HashMap<String, Collection<SafeUrl>> hashMap = new HashMap<>();
        List<SafeUrl> filesJs = selectorUrl.findWithExtensionFile(urls, ".js");
        List<SafeUrl> filesCss = selectorUrl.findWithExtensionFile(urls, ".css");
        List<SafeUrl> filesPng = selectorUrl.findWithExtensionFile(urls, ".png");
        List<SafeUrl> filesJson = selectorUrl.findWithExtensionFile(urls, ".json");
        List<SafeUrl> anotherWebPages = selectorUrl.findReferencesToAnotherPages(url, urls);

        hashMap.put(ALL_URLS, urls);
        hashMap.put(OTHERS_URLS, anotherWebPages);
        hashMap.put(JS_URL, filesJs);
        hashMap.put(CSS_URL, filesCss);
        hashMap.put(PNG_URL, filesPng);
        hashMap.put(JSON_URL, filesJson);

        return hashMap;
    }

    private HashMap<String, Integer> createUrlsCountResponse(
            HashMap<String, Collection<SafeUrl>> selectedUrls) {
        HashMap<String, Integer> hashMap = new HashMap<>();

        hashMap.put(ALL_URLS_NUMBER, selectedUrls.get(ALL_URLS).size());
        hashMap.put(JS_NUMBER, selectedUrls.get(JS_URL).size());
        hashMap.put(CSS_NUMBER, selectedUrls.get(CSS_URL).size());
        hashMap.put(PNG_NUMBER, selectedUrls.get(PNG_URL).size());
        hashMap.put(JSON_NUMBER, selectedUrls.get(JSON_URL).size());
        hashMap.put(OTHERS_URLS_NUMBER, selectedUrls.get(OTHERS_URLS).size());

        return hashMap;
    }
}
