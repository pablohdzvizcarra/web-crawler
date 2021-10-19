package jvm.pablo.webcrawler.extractor;

import java.util.Map;
import java.util.Set;

public interface ExtractorUrl {
    String extractHtmlStringToUrl(String url);

    Set<String> extractUrlsInsidePrimaryUrl(String htmlString);

    Map<Set<String>, Set<Object>> extractNestedUrls(String url);
}
