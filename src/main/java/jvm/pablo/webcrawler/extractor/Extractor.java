package jvm.pablo.webcrawler.extractor;

import java.util.List;
import java.util.Set;

public interface Extractor {
    String extractHtmlStringToUrl(String url);

    Set<String> extractUrlsInsidePrimaryUrl(String htmlString);

    List<Set<String>> extractNestedUrls(String url);
}
