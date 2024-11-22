package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateNumberCount(numbers);
        validateNumberRange(numbers);
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
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("로또번호는 6개의 숫자여야 합니다.");
        }
    }

    private void validateNumberRange(List<Integer> numbers) {
        boolean isInRange = numbers.stream()
                .anyMatch(number -> number >= 1 && number <= 45);
        if (!isInRange) {
            throw new IllegalArgumentException("로또번호는 1~45 사이여야 합니다.");
        }
    }

    private List<Integer> sortNumbers(List<Integer> numbers) {
        ArrayList<Integer> sortedNumbers = new ArrayList<>(numbers);
        Collections.sort(sortedNumbers);
        return sortedNumbers;
    }
}
