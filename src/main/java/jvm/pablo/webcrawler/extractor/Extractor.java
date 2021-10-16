package jvm.pablo.webcrawler.extractor;

import java.util.List;

public interface Extractor {
    String extractHtmlStringToUrl(String url);

    void extractUrlsInsideHtmlString(String htmlString);

    List<String> extractUrls(String url);
}
