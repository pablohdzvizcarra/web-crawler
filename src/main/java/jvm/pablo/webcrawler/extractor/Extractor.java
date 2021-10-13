package jvm.pablo.webcrawler.extractor;

import com.linkedin.urls.Url;
import com.linkedin.urls.detection.UrlDetector;
import com.linkedin.urls.detection.UrlDetectorOptions;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Extractor {

    public List<String> extractUrl(String urls) {
        UrlDetector parser = new UrlDetector(urls, UrlDetectorOptions.Default);
        List<Url> found = parser.detect();


        return found.stream()
                .map(Url::getFullUrl)
                .collect(Collectors.toUnmodifiableList());
    }
}
