package jvm.pablo.webcrawler.crawler;

import java.util.Collection;
import java.util.List;

import jvm.pablo.webcrawler.model.SafeUrl;


public interface SelectorUrl {

    List<SafeUrl> findWithExtensionFile(Collection<SafeUrl> urls, String extension);

    List<SafeUrl> findReferencesToAnotherPages(String url, Collection<SafeUrl> urlList);
}
