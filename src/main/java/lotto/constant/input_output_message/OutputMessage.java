package lotto.constant.input_output_message;

public enum OutputMessage {

    ISSUED_LOTTO_COUNT("%d개를 구매했습니다."),
    WINNING_RESULT_INTRODUCTION_START("당첨 통계\n---"),
    WINNING_RESULT_CONTENT("%d개 일치%s(%,d원) - %d개"),
    RATE_OF_RETURN_CONTENT("총 수익률은 %s입니다."),
    RATE_OF_RETURN_FORMAT("#.##"),
    PERCENT_SIGN("%"),
    SQUARE_BRACKETS("[%s]"),
    LOTTO_NUMBER_SEPARATOR(", "),
    MATCHED_BONUS_NUMBER_CONTENT(", 보너스 볼 일치 "),
    SPACE(" ");


    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
