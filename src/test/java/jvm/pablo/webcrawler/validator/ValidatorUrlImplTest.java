package jvm.pablo.webcrawler.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ValidatorUrlImplTest {
    private ValidatorUrl underTest;

    @BeforeEach
    void setUp() {
        underTest = new ValidatorUrlImpl();
    }

    @Test
    @DisplayName("Should check if the url have the correct format")
    void testThatValidateUrl() {
        String url = "https://github.com/PabloHdzVizcarra";

        boolean result = underTest.validateUrl(url);


        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Should return false with null parameter")
    void testThatWithNullValue() {
        boolean result = underTest.validateUrl(null);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Should return false when invalid url")
    void testThatNotValidParameter() {
        String invalidUrl = "https://pablo-urbano-hernandez-vizcarra.netlify.app/</a>";
        String invalidUrl2 = "https://github.com/PabloHdzVizcarra&quot;,&quot;user_id&quot;:null}}";

        boolean actual = underTest.validateUrl(invalidUrl);
        boolean actual2 = underTest.validateUrl(invalidUrl2);

        assertThat(actual).isFalse();
        assertThat(actual2).isFalse();
    }

    @Test
    @DisplayName("Should return false when pass a http url")
    void testThatValidatesHttpUrls() {
        String httpUrl = "http://github.com/PabloHdzVizcarra";

        boolean actualResult = underTest.validateHttpUrl(httpUrl);

        assertThat(actualResult).isTrue();
    }

    @Test
    @DisplayName("should clean the url with valid format")
    void testThatCleanValidUrl() {
        String malformedUrl = "https://pablo-urbano-hernandez-vizcarra.netlify.app/</a>";

        String actualUrl = underTest.cleanHtmlTagToUrl(malformedUrl, '<');

        assertThat(actualUrl).doesNotContain("<");
    }

    @Test
    @DisplayName("should return same url if the url not contains tag html <")
    void testThatReturnSameUrlWithCorrectFormat() {
        String url = "https://github.com/PabloHdzVizcarra";

        String actualUrl = underTest.cleanHtmlTagToUrl(url, '<');

        assertThat(actualUrl).isEqualTo(url);
    }
}