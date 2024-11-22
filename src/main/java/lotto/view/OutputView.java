package lotto.view;

public class OutputView {

    public void printBlankLine() {
        System.out.println();
    }

    public void printContent(String content) {
        System.out.println(content);
    }

    public void printError(String message) {
        String content = "[Error]" + message;
        System.out.println(content);
    }
}
