package jvm.pablo.webcrawler.extractor;

import com.linkedin.urls.Url;
import com.linkedin.urls.detection.UrlDetector;
import com.linkedin.urls.detection.UrlDetectorOptions;

import org.springframework.beans.factory.annotation.Autowired;
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
import jvm.pablo.webcrawler.validator.ValidatorUrl;

@Component
public class ExtractorImpl implements ExtractorUrl {

    private ValidatorUrl validator;

    public ExtractorImpl() {
    }

    @Autowired
    public ExtractorImpl(ValidatorUrl validator) {
        this.validator = validator;
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
    public Set<String> extractUrlsInsidePrimaryUrl(String url) {
        if (!validator.validateUrl(url))
            return null;

        String htmlString = extractHtmlStringToUrl(url);
        UrlDetector parser = new UrlDetector(htmlString, UrlDetectorOptions.QUOTE_MATCH);
        List<Url> urlList = parser.detect();

        return urlList.stream()
                .map(Url::getFullUrl)
                .filter(urlHttp -> !validator.validateHttpUrl(urlHttp))
                .map(urlHttps -> validator.cleanHtmlTagToUrl(urlHttps, '<'))
                .map(urlHttps -> validator.cleanHtmlTagToUrl(urlHttps, '>'))
                .filter(validator::validateUrl)
                .collect(Collectors.toSet());
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
