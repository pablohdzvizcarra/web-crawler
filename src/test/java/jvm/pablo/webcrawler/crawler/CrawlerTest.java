package jvm.pablo.webcrawler.crawler;

import org.junit.jupiter.api.Test;

import jvm.pablo.webcrawler.exception.InvalidUrlFormatException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

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

    @Test
    void testThatThrowUrlNotValidException() {
        String urlBad = "//github.com/PabloHdzVizcarra";
        Crawler crawler = new Crawler();

        assertThatThrownBy(() ->
                crawler.processUrl(urlBad))
                .isInstanceOf(InvalidUrlFormatException.class);

    }
}