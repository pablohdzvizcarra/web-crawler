package jvm.pablo.webcrawler.exception;

public class InvalidUrlFormatException extends RuntimeException {
    public InvalidUrlFormatException(String url) {
        super("The url: " + url + " is not valid, please check the format");
    }
}
