package jvm.pablo.webcrawler.crawler;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CrawlerTest {

    @Test
    void testThatProcessUrl() {
        String url = "https://github.com/PabloHdzVizcarra";
        Crawler crawler = new Crawler();

        String urlBody = crawler.processUrl(url);

        assertThat(urlBody)
                .withFailMessage("The urlBody cannot be null")
                .isNotEmpty();
    }
}