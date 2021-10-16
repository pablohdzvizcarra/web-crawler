package jvm.pablo.webcrawler.service;

import java.util.List;
import java.util.Set;

public interface CrawlerService {
    Set<String> findUrls(String url);

    List<Set<String>> findRecursive(String url);
}
