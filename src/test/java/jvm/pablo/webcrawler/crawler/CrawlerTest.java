package jvm.pablo.webcrawler.crawler;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import jvm.pablo.webcrawler.exception.InvalidUrlFormatException;
import jvm.pablo.webcrawler.extractor.ExtractorImpl;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CrawlerTest {
    private final String TEST_URL = "https://github.com/PabloHdzVizcarra";

    @Mock
    private ExtractorImpl extractor;

    @Test
    void testThatProcessUrl() {
        Crawler crawler = new Crawler();
        String urlBody = crawler.processUrl(TEST_URL);

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

    @Test
    void testThatExtractUrlsInsideBaseUrl() {
        Crawler crawler = new Crawler(extractor);
        String urlBody = crawler.processUrl(TEST_URL);

        when(extractor.extractUrls(anyString()))
                .thenReturn(Arrays.asList("google", "facebook"));

        List<String> urls = crawler.processSubUrls(urlBody);

        assertThat(urls)
                .asList()
                .isNotNull();
    }
}