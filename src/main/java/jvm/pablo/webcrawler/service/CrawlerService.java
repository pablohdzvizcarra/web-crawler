package jvm.pablo.webcrawler.service;

import java.util.List;
import java.util.Map;

public interface CrawlerService {
    List<String> findUrls(String url);

    List<Map<String, List<String>>> findRecursive(String url);
}
