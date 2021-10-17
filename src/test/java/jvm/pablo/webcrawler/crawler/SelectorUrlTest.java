package jvm.pablo.webcrawler.crawler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import jvm.pablo.webcrawler.selector.SelectorUrlImpl;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class SelectorUrlTest {
    private SelectorUrl underTest;

    @BeforeEach
    void setUp() {
        underTest = new SelectorUrlImpl();
    }

    @Test
    @DisplayName("should filter out urls ending with the extension")
    void testThatFindUrlsWithExtensionJavascript() {
        List<String> listUrls = createListUrlsWithExtension(".js");

        List<String> actualUrls = underTest.findWithExtensionFile(listUrls, ".js");
        int expectedSize = 3;

        assertThat(actualUrls.size())
                .isNotNull()
                .isEqualTo(expectedSize);
    }

    @Test
    void testThatEmptyList() {
        List<String> emptyList = new ArrayList<>();

        List<String> urls = underTest.findWithExtensionFile(emptyList, ".css");
        int actualSize = urls.size();
        int expectedSize = 0;

        assertThat(actualSize).isEqualTo(expectedSize);
    }

    private List<String> createListUrlsWithExtension(String extension) {
        return List.of(
                "https://use.typekit.net/mrg0hpc" + extension,
                "https://www.geeksforgeeks.org/category/algorithm/",
                "https://www.geeksforgeeks.org/category/algorithm/",
                "https://media.geeksforgeeks.org/cdn-uploads/20210727192049/CP_ad_icon" + extension,
                "https://media.geeksforgeeks.org/cdn-uploads/20210727192049/CP_ad_icon" + extension
                );
    }
}