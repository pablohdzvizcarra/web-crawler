package jvm.pablo.webcrawler.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import jvm.pablo.webcrawler.model.SafeUrl;

public interface CrawlerService {
    Set<SafeUrl> findUrls(String url);

    Map<Set<SafeUrl>, Set<String>> findRecursive(String url);

    HashMap<String, HashMap<String, ?>> findWithStatistics(String url);
}
