package jvm.pablo.webcrawler.extractor;

import com.linkedin.urls.Url;
import com.linkedin.urls.detection.UrlDetector;
import com.linkedin.urls.detection.UrlDetectorOptions;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Extractor {

    public List<String> extractUrls(String urls) {
        UrlDetector parser = new UrlDetector(urls, UrlDetectorOptions.JAVASCRIPT);
        List<Url> found = parser.detect();

        return formatList(found);
    }

    private List<String> formatList(List<Url> found) {
        List<String> list = new ArrayList<>();
        list.add("The url contains inside: " + found.size() + " urls");

        found.stream()
                .map(Url::getFullUrl)
                .filter(url -> url.startsWith("https://"))
                .forEach(list::add);

        return list;
    }
}
