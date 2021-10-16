package jvm.pablo.webcrawler.extractor;

import com.linkedin.urls.Url;
import com.linkedin.urls.detection.UrlDetector;
import com.linkedin.urls.detection.UrlDetectorOptions;

import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import jvm.pablo.webcrawler.exception.InvalidUrlFormatException;

@Component
public class ExtractorImpl implements Extractor{

    @Override
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

    @Override
    public String extractHtmlStringToUrl(String url) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();

            HttpClient client = HttpClient.newBuilder()
                    .build();

            HttpResponse<String> response = client.send(
                    request, HttpResponse.BodyHandlers.ofString());

            return response.body();

        } catch (Exception e) {
            throw new InvalidUrlFormatException(url);
        }
    }

    @Override
    public void extractUrlsInsideHtmlString(String htmlString) {

    }
}
