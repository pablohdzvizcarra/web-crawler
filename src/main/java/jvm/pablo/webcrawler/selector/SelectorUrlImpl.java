package jvm.pablo.webcrawler.selector;

import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import jvm.pablo.webcrawler.crawler.SelectorUrl;
import jvm.pablo.webcrawler.exception.InvalidUrlFormatException;

@Component
public class SelectorUrlImpl implements SelectorUrl {

    @Override
    public List<String> findWithExtensionFile(
            Collection<String> urls, String extension
    ) {
        return urls.stream()
                .filter(url -> url.endsWith(extension))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findReferencesToAnotherPages(String url, List<String> urlList) {
        String domain = getHostnameFromUrl(url);
        return urlList.stream()
                    .filter(eachUrl -> eachUrl.contains(domain))
                    .collect(Collectors.toList());
    }

    private String getHostnameFromUrl(String url) {
        URL validUrl = createUrl(url);
        String host = validUrl.getHost();
        int indexPoint = host.indexOf('.');
        return host.substring(0, indexPoint);
    }

    private URL createUrl(String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            throw new InvalidUrlFormatException(url);
        }
    }
}
