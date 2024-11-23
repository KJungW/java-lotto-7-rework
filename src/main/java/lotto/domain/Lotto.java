package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.constant.LottoSetting;
import lotto.constant.exception_message.DomainExceptionMessage;
import lotto.exception.WrongInputException;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateNumberCount(numbers);
        validateNumberRange(numbers);
        validateNumberDuplicate(numbers);
        this.numbers = sortNumbers(numbers);
    }

    public int calculateMatchedNumberCount(Lotto winningLotto) {
        Set<Integer> winningNumbers = new HashSet<>(winningLotto.getNumbers());
        return numbers.stream()
                .filter(winningNumbers::contains).toList().size();
    }

    public boolean checkBonusNumberMatching(int bonusNumber) {
        return numbers.stream().anyMatch(number -> number == bonusNumber);
    }

    public Lotto copy() {
        return new Lotto(numbers.stream().toList());
    }

    public List<Integer> getNumbers() {
        return numbers.stream().toList();
    }

    private void validateNumberCount(List<Integer> numbers) {
        if (numbers.size() != LottoSetting.NUMBER_COUNT) {
            throw new IllegalArgumentException(DomainExceptionMessage.LOTTO_NUMBER_COUNT_IS_WRONG.getMessage());
        }
    }

    private void validateNumberRange(List<Integer> numbers) {
        boolean isInRange = numbers.stream()
                .allMatch(number -> number >= LottoSetting.MINIMUM_NUMBER && number <= LottoSetting.MAXIMUM_NUMBER);
        if (!isInRange) {
            throw new IllegalArgumentException(DomainExceptionMessage.LOTTO_NUMBER_RANGE_IS_WRONG.getMessage());
        }
    }

    private void validateNumberDuplicate(List<Integer> numbers) {
        Set<Integer> nonDuplicatedNumbers = new HashSet<>(numbers);
        if (nonDuplicatedNumbers.size() != numbers.size()) {
            throw new WrongInputException(DomainExceptionMessage.DUPLICATED_LOTTO_NUMBER_IS_NOT_ALLOWED.getMessage());
        }
    }

    private List<Integer> sortNumbers(List<Integer> numbers) {
        ArrayList<Integer> sortedNumbers = new ArrayList<>(numbers);
        Collections.sort(sortedNumbers);
        return sortedNumbers;
    }
}
