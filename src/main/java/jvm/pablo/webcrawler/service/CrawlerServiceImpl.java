package jvm.pablo.webcrawler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import jvm.pablo.webcrawler.crawler.Crawler;

@Service
public class CrawlerServiceImpl implements CrawlerService {
    private final Crawler crawler;

    @Autowired
    public CrawlerServiceImpl(Crawler crawler) {
        this.crawler = crawler;
    }

    @Override
    public List<String> findUrls(String url) {
        String htmlString = crawler.processUrl(url);
        return crawler.processSubUrls(htmlString);
    }
}
