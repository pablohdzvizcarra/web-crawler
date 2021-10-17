package jvm.pablo.webcrawler.extractor;

import java.util.List;
import java.util.Set;

public interface ExtractorUrl {
    String extractHtmlStringToUrl(String url);

    Set<String> extractUrlsInsidePrimaryUrl(String htmlString);

    List<Set<String>> extractNestedUrls(String url);
}
