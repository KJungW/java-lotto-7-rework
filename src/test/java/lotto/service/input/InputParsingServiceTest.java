package lotto.service.input;

import lotto.domain.Lotto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputParsingServiceTest {

    private InputParsingService inputParsingService;

    @BeforeEach
    void beforeEach() {
        inputParsingService = new InputParsingService();
    }

    @DisplayName("Lotto 파싱 - 정상흐름")
    @Test
    void Lotto_파싱_정상흐름() {
        String rawInput = "1,2,3,4,5,6";

        Lotto lotto = inputParsingService.parseLotto(rawInput);

        Assertions.assertThat(lotto.getNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
    }

}