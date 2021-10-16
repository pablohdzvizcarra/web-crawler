package jvm.pablo.webcrawler.extractor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


class ExtractorTest {
    private ExtractorImpl extractor;

    @BeforeEach
    void setUp() {
        extractor = new ExtractorImpl();
    }

    @Test
    void testThatGetUrlsInsideString() {
        String urls = "data feo todos https://www.github.com/PabloHdzVizcarra  asdkljnasdnj " +
                "::: https://www.baeldung.com/assertj-exception-assertion simple-data";
        int expectedSize = 3;

        List<String> actual = extractor.extractUrls(urls);

        assertThat(actual.size()).isEqualTo(expectedSize);
    }

    @Test
    void testThatMustReturnFirstLineNumberUrlsMessage() {
        String urls = "data feo todos https://www.github.com/PabloHdzVizcarra" +
                "::: https://www.baeldung.com/assertj-exception-assertion simple-data";

        List<String> list = extractor.extractUrls(urls);
        String actualFirstLine = list.get(0);
        int size = list.size() - 1;
        String expectedLine = "inside: " + size;

        assertThat(actualFirstLine).contains(expectedLine);
    }

    @Test
    void testThatFilterByInvalidLink() {
        String urls = "data feo todos https://www.github.com/PabloHdzVizcarra" +
                "::: https://www.baeldung.com/assertj-exception-assertion simple-data" +
                "http://data.com";

        List<String> list = extractor.extractUrls(urls);
        int actualSize = list.size();
        int expectedSize = 3;

        assertThat(actualSize).isEqualTo(expectedSize);
    }
}