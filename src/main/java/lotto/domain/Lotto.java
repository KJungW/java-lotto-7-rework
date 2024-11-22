package lotto.domain;

import java.util.List;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateNumberCount(numbers);
        validateNumberRange(numbers);
        this.numbers = numbers;
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

    public Lotto copy() {
        return new Lotto(numbers.stream().toList());
    }
}
