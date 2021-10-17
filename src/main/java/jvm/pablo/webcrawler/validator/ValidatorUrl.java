package jvm.pablo.webcrawler.validator;

public interface ValidatorUrl {

    boolean validateUrl(String url);

    boolean validateHttpUrl(String httpUrl);

    String cleanHtmlTagToUrl(String malformedUrl, char tagHtml);
}
