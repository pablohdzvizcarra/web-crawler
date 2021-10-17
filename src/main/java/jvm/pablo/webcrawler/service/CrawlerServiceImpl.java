package jvm.pablo.webcrawler.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import jvm.pablo.webcrawler.crawler.Crawler;
import jvm.pablo.webcrawler.exception.InvalidUrlFormatException;

@Service
public class CrawlerServiceImpl implements CrawlerService {
    Logger logger = LoggerFactory.getLogger(CrawlerServiceImpl.class);

    private final Crawler crawler;

    @Autowired
    public CrawlerServiceImpl(Crawler crawler) {
        this.crawler = crawler;
    }

    @Override
    public Set<String> findUrls(String url) {
        Set<String> urlList = crawler.processUrl(url);

        if (urlList.size() == 0)
            throw new InvalidUrlFormatException(url);

        logger.info(
                "Find: " + urlList.size() + " urls inside the passed url: " + url
        );

        return urlList;
    }

    @Override
    public Map<Set<String>, Set<String>> findRecursive(String url) {
        return crawler.recursiveFindUrls(url);
    }

    @Override
    public HashMap<String, HashMap<String, ?>> findWithStatistics(String url) {
        return crawler.findUrlsWithStatistics(url);
    }

}
