package lotto.constant;

public enum InputSeparator {
    COMMA(",");

    private final String content;

    InputSeparator(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
