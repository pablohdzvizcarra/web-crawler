package jvm.pablo.webcrawler.crawler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import jvm.pablo.webcrawler.exception.InvalidUrlFormatException;
import jvm.pablo.webcrawler.extractor.Extractor;

@Component
public class Crawler {

    private Extractor extractor;

    public Crawler() {
    }

    @Autowired
    public Crawler(Extractor extractor) {
        this.extractor = extractor;
    }

    public String processUrl(String url) {

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
}
