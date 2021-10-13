package jvm.pablo.webcrawler.extractor;

import com.linkedin.urls.Url;
import com.linkedin.urls.detection.UrlDetector;
import com.linkedin.urls.detection.UrlDetectorOptions;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Extractor {

    public List<String> extractUrl(String urls) {
        List<String> urlsList = new ArrayList<>();
        UrlDetector parser = new UrlDetector(urls, UrlDetectorOptions.Default);
        List<Url> found = parser.detect();

        urlsList.add("The url contains inside: " + found.size() + " urls");

        found.forEach(url ->
                urlsList.add(url.getFullUrl())
        );

        return urlsList;
    }
}
