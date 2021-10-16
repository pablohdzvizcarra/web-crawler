package jvm.pablo.webcrawler.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CrawlerService {
    Set<String> findUrls(String url);

    List<Map<String, List<String>>> findRecursive(String url);
}
