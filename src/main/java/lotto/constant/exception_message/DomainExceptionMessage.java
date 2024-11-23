package lotto.constant.exception_message;

public enum DomainExceptionMessage {
    LOTTO_NUMBER_COUNT_IS_WRONG("로또번호는 6개의 숫자여야 합니다."),
    LOTTO_NUMBER_RANGE_IS_WRONG("로또번호는 1~45 사이여야 합니다."),
    DUPLICATED_LOTTO_NUMBER_IS_NOT_ALLOWED("로또번호는 중복을 허용하지 않습니다.");

    private final String message;

    DomainExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
