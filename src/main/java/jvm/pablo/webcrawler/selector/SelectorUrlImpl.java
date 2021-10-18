package jvm.pablo.webcrawler.selector;

import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import jvm.pablo.webcrawler.crawler.SelectorUrl;

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
        try {
            URL url1 = new URL(url);
            String host = url1.getHost();
            int indexPoint = host.indexOf('.');
            String domain = host.substring(0, indexPoint);

            return urlList.stream()
                    .filter(eachUrl -> eachUrl.contains(domain))
                    .collect(Collectors.toList());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();

    }
}
