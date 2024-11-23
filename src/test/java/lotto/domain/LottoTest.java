package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;
import lotto.constant.exception_message.DomainExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class LottoTest {

    @DisplayName("로또 생성 - 정상흐름")
    @Test
    void 로또_생성_정상흐름() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        assertThat(lotto.getNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @DisplayName("로또 생성 - 잘못된 번호 개수")
    @Test
    void 로또_생성_잘못된_번호_개수() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .withMessage(DomainExceptionMessage.LOTTO_NUMBER_COUNT_IS_WRONG.getMessage());
    }

    @DisplayName("로또 생성 - 잘못된 번호 범위")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46})
    void 로또_생성_잘못된_번호_범위(int outOfRangeNumber) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, outOfRangeNumber)))
                .withMessage(DomainExceptionMessage.LOTTO_NUMBER_RANGE_IS_WRONG.getMessage());
    }

    @DisplayName("로또 생성 - 번호 중복")
    @Test
    void 로또_생성_번호_중복() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .withMessage(DomainExceptionMessage.DUPLICATED_LOTTO_NUMBER_IS_NOT_ALLOWED.getMessage());
    }

    @DisplayName("매칭 번호 계산 - 정상 흐름")
    @Test
    void 매칭_번호_계산_정상_흐름() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        int matchedNumberCount = lotto.calculateMatchedNumberCount(new Lotto(List.of(1, 2, 3, 41, 42, 43)));

        assertThat(matchedNumberCount).isEqualTo(3);
    }

    @DisplayName("보너스 번호 매칭 체크 - 정상흐름")
    @ParameterizedTest
    @CsvSource(value = {"6,true", "7,false"})
    void 보너스_번호_매칭_체크_정상흐름(int bonusNumber, boolean expectedIsMatched) {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        boolean isMatched = lotto.checkBonusNumberMatching(bonusNumber);

        assertThat(isMatched).isEqualTo(expectedIsMatched);
    }

}