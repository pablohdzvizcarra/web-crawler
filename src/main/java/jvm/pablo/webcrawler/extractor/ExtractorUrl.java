package jvm.pablo.webcrawler.extractor;

import java.util.Map;
import java.util.Set;

import jvm.pablo.webcrawler.model.SafeUrl;

public interface ExtractorUrl {
    String extractHtmlStringToUrl(String url);

    Set<SafeUrl> extractUrlsInsidePrimaryUrl(String htmlString);

    Map<Set<SafeUrl>, Set<String>> extractNestedUrls(String url);
}
