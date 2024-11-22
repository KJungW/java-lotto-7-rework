package lotto.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.exception.WrongInputException;
import lotto.view.InputView;
import lotto.view.OutputView;

public class InputService {

    private final InputView inputView;
    private final OutputView outputView;

    public InputService(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public int inputPurchaseAmount() {
        while (true) {
            try {
                return tryInputPurchaseAmount();
            } catch (WrongInputException exception) {
                outputView.printError(exception.getMessage());
            } catch (IllegalArgumentException exception) {
                outputView.printError("잘못된 입력입니다. 다시 입력해주세요!");
            }
        }
    }

    public Lotto inputWinningNumber() {
        while (true) {
            try {
                return tryInputWinningNumber();
            } catch (WrongInputException exception) {
                outputView.printError(exception.getMessage());
            } catch (IllegalArgumentException exception) {
                outputView.printError("잘못된 입력입니다. 다시 입력해주세요!");
            }
        }
    }

    private int tryInputPurchaseAmount() {
        String rawInput = inputView.inputPurchaseAmount();
        validateLottoNumberIsNumeric(rawInput);
        int purchaseAmount = Integer.parseInt(rawInput);
        validatePurchaseAmountUnit(purchaseAmount);
        return purchaseAmount;
    }

    private Lotto tryInputWinningNumber() {
        String rawInput = inputView.inputWinningNumber();
        validateLottoNumberSize(rawInput);
        validateLottoNumbersAreNumeric(rawInput);
        validateLottoNumberRange(rawInput);
        validateLottoNumberDuplication(rawInput);
        return parseLotto(rawInput);
    }

    private void validateLottoNumberIsNumeric(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new WrongInputException("로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }

    private void validatePurchaseAmountUnit(int purchaseAmount) {
        if (purchaseAmount % 1000 != 0) {
            throw new WrongInputException("로또 구매 금액은 1000단위여야합니다.");
        }
    }

    private void validateLottoNumberSize(String rawInput) {
        List<String> rawNumbers = List.of(rawInput.split(","));
        if (rawNumbers.size() != 6) {
            throw new WrongInputException("로또번호는 6개의 숫자여야 합니다.");
        }
    }

    private void validateLottoNumbersAreNumeric(String rawInput) {
        List<String> rawNumbers = List.of(rawInput.split(","));
        rawNumbers.forEach(this::validateLottoNumberIsNumeric);
    }

    private void validateLottoNumberRange(String rawInput) {
        List<String> rawNumbers = List.of(rawInput.split(","));
        for (String rawNumber : rawNumbers) {
            int number = Integer.parseInt(rawNumber);
            if (number < 1 || number > 45) {
                throw new WrongInputException("로또번호의 범위는 1부터 45여야 합니다.");
            }
        }
    }

    private void validateLottoNumberDuplication(String rawInput) {
        List<String> rawNumbers = List.of(rawInput.split(","));
        Set<Integer> numbers = rawNumbers.stream().map(Integer::parseInt).collect(Collectors.toSet());
        if (numbers.size() != rawNumbers.size()) {
            throw new WrongInputException("로또번호는 중복을 허용하지 않습니다.");
        }
    }

    private Lotto parseLotto(String rawInput) {
        List<String> rawNumbers = List.of(rawInput.split(","));
        List<Integer> numbers = rawNumbers.stream().map(Integer::parseInt).toList();
        return new Lotto(numbers);
    }
}
