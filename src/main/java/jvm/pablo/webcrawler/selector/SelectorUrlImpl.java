package jvm.pablo.webcrawler.selector;

import org.springframework.stereotype.Component;

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
}
