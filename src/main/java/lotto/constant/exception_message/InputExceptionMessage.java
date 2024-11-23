package lotto.constant.exception_message;

public enum InputExceptionMessage {
    LOTTO_NUMBER_COUNT_IS_WRONG("로또번호는 6개의 숫자여야 합니다."),
    LOTTO_NUMBER_RANGE_IS_WRONG("로또 번호는 1부터 45 사이의 숫자여야 합니다."),
    DUPLICATED_LOTTO_NUMBER_IS_NOT_ALLOWED("로또번호는 중복을 허용하지 않습니다."),
    LOTTO_PURCHASE_AMOUNT_IS_WRONG("로또 구매 금액은 1000단위여야합니다."),
    BONUS_NUMBER_IS_NOT_NUMERIC("보너스 번호는 숫자여야 합니다."),
    BONUS_NUMBER_RANGE_IS_NOT_ALLOWED("보너스 번호의 범위는 1~45여야 합니다."),
    DUPLICATED_BONUS_NUMBER_IS_NOT_ALLOWED("보너스번호는 당첨 번호와의 중복을 허용하지 않습니다."),
    UNEXPECTED_INPUT_EXCEPTION_MESSAGE("잘못된 입력입니다. 다시 입력해주세요!");

    private final String message;

    InputExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
