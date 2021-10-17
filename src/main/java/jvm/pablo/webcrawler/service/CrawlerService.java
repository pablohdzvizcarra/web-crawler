package jvm.pablo.webcrawler.service;

import java.util.Map;
import java.util.Set;

public interface CrawlerService {
    Set<String> findUrls(String url);

    Map<Set<String>, Set<String>> findRecursive(String url);
}
