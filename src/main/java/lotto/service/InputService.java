package lotto.service;

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

    private int tryInputPurchaseAmount() {
        String rawInput = inputView.inputPurchaseAmount();
        validateInputIsNumberFormat(rawInput);
        int purchaseAmount = Integer.parseInt(rawInput);
        validatePurchaseAmountUnit(purchaseAmount);
        return purchaseAmount;
    }

    private void validateInputIsNumberFormat(String input) {
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
}
