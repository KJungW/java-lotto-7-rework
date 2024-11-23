package lotto.service.input;

import java.util.List;
import lotto.constant.InputSeparator;
import lotto.domain.Lotto;

public class InputParsingService {

    public Lotto parseLotto(String rawInput) {
        List<String> rawNumbers = List.of(rawInput.split(InputSeparator.COMMA.getContent()));
        List<Integer> numbers = rawNumbers.stream().map(Integer::parseInt).toList();
        return new Lotto(numbers);
    }
}
