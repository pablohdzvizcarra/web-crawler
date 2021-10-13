package jvm.pablo.webcrawler.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import jvm.pablo.webcrawler.crawler.Crawler;

@Service
public class CrawlerServiceImpl implements CrawlerService {
    Logger logger = LoggerFactory.getLogger(CrawlerServiceImpl.class);

    private final Crawler crawler;

    @Autowired
    public CrawlerServiceImpl(Crawler crawler) {
        this.crawler = crawler;
    }

    @Override
    public List<String> findUrls(String url) {
        String htmlString = crawler.processUrl(url);
        List<String> urlList = crawler.processSubUrls(htmlString);

        logger.info(
                "Find: " + urlList.size() + " urls inside the passed url: " + url
        );

        return urlList;
    }
}
