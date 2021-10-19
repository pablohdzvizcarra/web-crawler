package jvm.pablo.webcrawler.selector;

import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import jvm.pablo.webcrawler.crawler.SelectorUrl;
import jvm.pablo.webcrawler.exception.InvalidUrlFormatException;
import jvm.pablo.webcrawler.model.SafeUrl;

@Component
public class SelectorUrlImpl implements SelectorUrl {

    @Override
    public List<SafeUrl> findWithExtensionFile(
            Collection<SafeUrl> urls, String extension
    ) {
        return urls.stream()
                .map(SafeUrl::getValue)
                .filter(url -> url.endsWith(extension))
                .map(SafeUrl::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<SafeUrl> findReferencesToAnotherPages(String url, Collection<SafeUrl> urlList) {
        String domain = getHostnameFromUrl(url);
        return urlList.stream()
                .map(SafeUrl::getValue)
                .filter(eachUrl -> !eachUrl.contains(domain))
                .map(SafeUrl::new)
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
