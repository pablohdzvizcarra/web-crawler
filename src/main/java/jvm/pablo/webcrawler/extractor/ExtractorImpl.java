package jvm.pablo.webcrawler.extractor;

import com.linkedin.urls.Url;
import com.linkedin.urls.detection.UrlDetector;
import com.linkedin.urls.detection.UrlDetectorOptions;

import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import jvm.pablo.webcrawler.exception.InvalidUrlFormatException;

@Component
public class ExtractorImpl implements Extractor {

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
    public Set<String> extractUrlsInsidePrimaryUrl(String url) {
        if (!urlIsValid(url))
            return null;

        String htmlString = extractHtmlStringToUrl(url);
        UrlDetector parser = new UrlDetector(htmlString, UrlDetectorOptions.QUOTE_MATCH);
        List<Url> urlList = parser.detect();

        return urlList.stream()
                .map(Url::getFullUrl)
                .filter(subUrl -> subUrl.startsWith("https://"))
//                .filter(this::urlIsValid)
                .collect(Collectors.toSet());
    }

    private boolean urlIsValid(String url) {
        String regex = "((http|https)://)(www.)?"
                + "[a-zA-Z0-9@:%._+~#?&/=]"
                + "{2,256}\\.[a-z]"
                + "{2,6}\\b([-a-zA-Z0-9@:%"
                + "._+~#?&/=]*)";

        Pattern pattern = Pattern.compile(regex);

        if (url == null)
            return false;
        Matcher matcher = pattern.matcher(url);
        return matcher.matches();
    }

    @Override
    public List<Set<String>> extractNestedUrls(String url) {
        Set<String> urls = extractUrlsInsidePrimaryUrl(url);

        return urls.stream()
                .map(this::extractUrlsInsidePrimaryUrl)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
