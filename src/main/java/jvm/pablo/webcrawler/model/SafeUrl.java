package jvm.pablo.webcrawler.model;

public class SafeUrl {
    private String value;

    public SafeUrl(String url) {
        this.value = url;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SafeUrl{" +
                "value='" + value + '\'' +
                '}';
    }
}
