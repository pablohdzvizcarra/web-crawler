package jvm.pablo.webcrawler.extractor;

import com.linkedin.urls.Url;
import com.linkedin.urls.detection.UrlDetector;
import com.linkedin.urls.detection.UrlDetectorOptions;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Extractor {

    public List<String> extractUrls(String urls) {
        UrlDetector parser = new UrlDetector(urls, UrlDetectorOptions.Default);
        List<Url> found = parser.detect();

        return formatList(found);
    }

    private List<String> formatList(List<Url> found) {
        List<String> list = new ArrayList<>();
        list.add("The url contains inside: " + found.size() + " urls");

        found.forEach(url ->
                list.add(url.getFullUrl())
        );

        return list;
    }
}
