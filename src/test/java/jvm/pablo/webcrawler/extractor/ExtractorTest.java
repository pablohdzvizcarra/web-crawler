package jvm.pablo.webcrawler.extractor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import jvm.pablo.webcrawler.exception.InvalidUrlFormatException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class ExtractorTest {
    private ExtractorUrl extractor;

    @BeforeEach
    void setUp() {
        extractor = new ExtractorImpl();
    }

    @Test
    @DisplayName("Extract HTML in format String to URL")
    void extractHtmlStringToUrl() {
        String url = "https://github.com/PabloHdzVizcarra";

        String actualHtmlString = extractor.extractHtmlStringToUrl(url);
        String expectFormat = "<!DOCTYPE html>";

        assertThat(actualHtmlString)
                .withFailMessage("The String extracted not correct format to HTML file")
                .contains(expectFormat);
    }

    @Test
    @DisplayName("Should extract all urls inside the HTML with format String")
    void extractUrlsInsideHtmlString() {
        String url = "https://github.com/PabloHdzVizcarra";

        Set<String> actualUrlList = extractor.extractUrlsInsidePrimaryUrl(url);
        
        assertThat(actualUrlList.size() > 0).isTrue();
    }

    @Test
    @DisplayName("Should be thrown a custom exception when url format isn't valid")
    void testThatThrownExceptionWhenUrlFormatIsInvalid() {
        String invalidUrl = "htt//invalid-url";

        assertThatThrownBy(() -> extractor.extractHtmlStringToUrl(invalidUrl))
                .isInstanceOf(InvalidUrlFormatException.class);
    }

    @Test
    @DisplayName("Should extract urls nested in another url")
    void testThatExtractUrlsInsideAnotherUrls() {
        String url = "https://github.com/EnzoDiazDev";

        List<Set<String>> actualNestedUrls = extractor.extractNestedUrls(url);

        assertThat(actualNestedUrls != null).isTrue();
    }
}