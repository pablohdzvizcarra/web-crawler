package jvm.pablo.webcrawler.validator;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ValidatorUrlImpl implements ValidatorUrl {
    @Override
    public boolean validateUrl(String url) {
        if (url == null)
            return false;

        String regex = "((http|https)://)(www.)?"
                + "[a-zA-Z0-9@:%._+~#?&/=]"
                + "{2,256}\\.[a-z]"
                + "{2,6}\\b([-a-zA-Z0-9@:%"
                + "._+~#?&/=]*)";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(url);
        return matcher.matches();
    }

    @Override
    public boolean validateHttpUrl(String httpUrl) {
        if (httpUrl == null)
            return false;
        return httpUrl.startsWith("http://");
    }

    @Override
    public String cleanHtmlTagToUrl(String malformedUrl) {
        int charIndex = malformedUrl.indexOf('<');

        if (charIndex < 0)
            return malformedUrl;

        return malformedUrl.substring(0, charIndex);
    }
}
