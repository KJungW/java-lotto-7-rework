package lotto.constant.exception_message;

public enum UtilityExceptionMessage {
    RANDOM_NUMBER_RANGE_IS_WRONG("잘못된 랜덤번호 범위 설정입니다");

    private final String message;

    UtilityExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
