package jvm.pablo.webcrawler.service;

import java.util.List;

public interface CrawlerService {
    List<String> findUrls(String url);
}
