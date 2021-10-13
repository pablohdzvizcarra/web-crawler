package jvm.pablo.webcrawler.extractor;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


class ExtractorTest {

    @Test
    void testThatGetUrlsInsideString() {
        Extractor underTest = new Extractor();
        String urls = "data feo todos https://github.com/PabloHdzVizcarra  asdkljnasdnj " +
                "::: https://www.baeldung.com/assertj-exception-assertion simple-data";
        int expectedSize = 2;

        List<String> actual = underTest.extractUrl(urls);

        assertThat(actual.size()).isEqualTo(expectedSize);
    }
}