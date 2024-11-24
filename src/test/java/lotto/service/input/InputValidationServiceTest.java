package lotto.service.input;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;
import lotto.constant.exception_message.InputExceptionMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputValidationServiceTest {

    private InputValidationService inputValidationService;

    @BeforeEach
    void beforeEach() {
        inputValidationService = new InputValidationService();
    }

    @DisplayName("로또 구매금액 단위 검증 - 통과")
    @Test
    void 로또_구매금액_단위_검증_통과() {
        int input = 5000;

        assertThatCode(() -> inputValidationService.validatePurchaseAmountUnit(input))
                .doesNotThrowAnyException();
    }

    @DisplayName("로또 구매금액 단위 검증 - 실패")
    @Test
    void 로또_구매금액_단위_검증_실패() {
        int input = 5500;

        assertThatIllegalArgumentException()
                .isThrownBy(() -> inputValidationService.validatePurchaseAmountUnit(input))
                .withMessage(InputExceptionMessage.LOTTO_PURCHASE_AMOUNT_IS_WRONG.getMessage());
    }

    @DisplayName("로또번호 입력값이 숫자형인지 검증 - 통과")
    @Test
    void 로또번호_입력값이_숫자형인지_검증_통과() {
        String input = "10";

        assertThatCode(() -> inputValidationService.validateLottoNumberIsNumeric(input))
                .doesNotThrowAnyException();
    }

    @DisplayName("로또번호 입력값이 숫자형인지 검증 - 실패")
    @Test
    void 로또번호_입력값이_숫자형인지_검증_실패() {
        String input = "ten";

        assertThatIllegalArgumentException()
                .isThrownBy(() -> inputValidationService.validateLottoNumberIsNumeric(input))
                .withMessage(InputExceptionMessage.LOTTO_NUMBER_RANGE_IS_WRONG.getMessage());
    }

    @DisplayName("로또 번호들이 숫자형인지 검증 - 통과")
    @Test
    void 로또_번호들이_숫자형인지_검증_통과() {
        String input = "1,2,3,4,5,6";

        assertThatCode(() -> inputValidationService.validateLottoNumbersAreNumeric(input))
                .doesNotThrowAnyException();
    }

    @DisplayName("로또 번호들이 숫자형인지 검증 - 실패")
    @Test
    void 로또_번호들이_숫자형인지_검증_실패() {
        String input = "1,two,3,4,5,6";

        assertThatIllegalArgumentException()
                .isThrownBy(() -> inputValidationService.validateLottoNumbersAreNumeric(input))
                .withMessage(InputExceptionMessage.LOTTO_NUMBER_RANGE_IS_WRONG.getMessage());
    }

    @DisplayName("로또 번호 개수 검증 - 통과")
    @Test
    void 로또_번호_개수_검증_통과() {
        String input = "1,2,3,4,5,6";

        assertThatCode(() -> inputValidationService.validateLottoNumberCount(input))
                .doesNotThrowAnyException();
    }

    @DisplayName("로또 번호 개수 검증 - 실패")
    @Test
    void 로또_번호_개수_검증_실패() {
        String input = "1,2,3,4,5";

        assertThatIllegalArgumentException()
                .isThrownBy(() -> inputValidationService.validateLottoNumberCount(input))
                .withMessage(InputExceptionMessage.LOTTO_NUMBER_COUNT_IS_WRONG.getMessage());
    }

    @DisplayName("로또 번호 범위 검증 - 통과")
    @Test
    void 로또_번호_범위_검증_통과() {
        String input = "1,2,3,4,5,6";

        assertThatCode(() -> inputValidationService.validateLottoNumberRange(input))
                .doesNotThrowAnyException();
    }

    @DisplayName("로또 번호 범위 검증 - 실패")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46, 47})
    void 로또_번호_범위_검증_실패(int numberOutOfRange) {
        String input = "1,2,3,4,5," + numberOutOfRange;

        assertThatIllegalArgumentException()
                .isThrownBy(() -> inputValidationService.validateLottoNumberRange(input))
                .withMessage(InputExceptionMessage.LOTTO_NUMBER_RANGE_IS_WRONG.getMessage());
    }

    @DisplayName("로또 번호 중복 검증 - 통과")
    @Test
    void 로또_번호_중복_검증_통과() {
        String input = "1,2,3,4,5,6";

        assertThatCode(() -> inputValidationService.validateLottoNumberDuplication(input))
                .doesNotThrowAnyException();
    }

    @DisplayName("로또 번호 중복 검증 - 실패")
    @Test
    void 로또_번호_중복_검증_실패() {
        String input = "1,2,3,4,5,5";

        assertThatIllegalArgumentException()
                .isThrownBy(() -> inputValidationService.validateLottoNumberDuplication(input))
                .withMessage(InputExceptionMessage.DUPLICATED_LOTTO_NUMBER_IS_NOT_ALLOWED.getMessage());
    }

    @DisplayName("보너스 번호가 숫자형인지 검증 - 통과")
    @Test
    void 보너스_번호가_숫자형인지_검증_통과() {
        String input = "1";

        assertThatCode(() -> inputValidationService.validateBonusNumberIsNumeric(input))
                .doesNotThrowAnyException();
    }

    @DisplayName("보너스 번호가 숫자형인지 검증 - 실패")
    @Test
    void 보너스_번호가_숫자형인지_검증_실패() {
        String input = "one";

        assertThatIllegalArgumentException()
                .isThrownBy(() -> inputValidationService.validateBonusNumberIsNumeric(input))
                .withMessage(InputExceptionMessage.BONUS_NUMBER_IS_NOT_NUMERIC.getMessage());
    }

    @DisplayName("보너스 번호 범위 검증 - 통과")
    @Test
    void 보너스_번호_범위_검증_통과() {
        String input = "1";

        assertThatCode(() -> inputValidationService.validateBonusNumberIsRange(input))
                .doesNotThrowAnyException();
    }

    @DisplayName("보너스 번호 범위 검증 - 실패")
    @ParameterizedTest
    @ValueSource(strings = {"-1", "0", "46", "47"})
    void 보너스_번호_범위_검증_실패(String input) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> inputValidationService.validateBonusNumberIsRange(input))
                .withMessage(InputExceptionMessage.BONUS_NUMBER_RANGE_IS_NOT_ALLOWED.getMessage());
    }

    @DisplayName("보너스 번호 벤 검증 - 통과")
    @Test
    void 보너스_번호_벤_검증_통과() {
        String input = "1";
        List<Integer> bannedNumbers = List.of(10, 11, 12, 13, 14, 15);

        assertThatCode(() -> inputValidationService.validateBonusNumberBan(input, bannedNumbers))
                .doesNotThrowAnyException();
    }

    @DisplayName("보너스 번호 벤 검증 - 실패")
    @Test
    void 보너스_번호_벤_검증_실패() {
        String input = "10";
        List<Integer> bannedNumbers = List.of(10, 11, 12, 13, 14, 15);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> inputValidationService.validateBonusNumberBan(input, bannedNumbers))
                .withMessage(InputExceptionMessage.DUPLICATED_BONUS_NUMBER_IS_NOT_ALLOWED.getMessage());
    }
}