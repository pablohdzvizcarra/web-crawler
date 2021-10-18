package jvm.pablo.webcrawler.crawler;

import java.util.Collection;
import java.util.List;


public interface SelectorUrl {

    List<String> findWithExtensionFile(Collection<String> urls, String extension);

    List<String> findReferencesToAnotherPages(String url, List<String> urlList);
}
