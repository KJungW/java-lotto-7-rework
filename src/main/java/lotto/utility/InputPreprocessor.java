package lotto.utility;

public class InputPreprocessor {

    private static final String SPACE = " ";
    private static final String BLANK = "";

    public static String removeSpace(String input) {
        return input.strip().replace(SPACE, BLANK);
    }
}
