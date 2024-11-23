package lotto.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lotto.constant.DefaultValue;
import lotto.constant.InputSeparator;
import lotto.constant.LottoSetting;
import lotto.constant.exception_message.InputExceptionMessage;
import lotto.domain.Lotto;
import lotto.exception.WrongInputException;
import lotto.utility.InputPreprocessor;
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
                outputView.printError(InputExceptionMessage.UNEXPECTED_INPUT_EXCEPTION_MESSAGE.getMessage());
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
                outputView.printError(InputExceptionMessage.UNEXPECTED_INPUT_EXCEPTION_MESSAGE.getMessage());
            }
        }
    }

    public int inputBonusNumber(List<Integer> bannedNumbers) {
        while (true) {
            try {
                return tryInputBonusNumber(bannedNumbers);
            } catch (WrongInputException exception) {
                outputView.printError(exception.getMessage());
            } catch (IllegalArgumentException exception) {
                outputView.printError(InputExceptionMessage.UNEXPECTED_INPUT_EXCEPTION_MESSAGE.getMessage());
            }
        }
    }

    private int tryInputPurchaseAmount() {
        String rawInput = inputView.inputPurchaseAmount();
        rawInput = InputPreprocessor.removeSpace(rawInput);
        validateLottoNumberIsNumeric(rawInput);
        int purchaseAmount = Integer.parseInt(rawInput);
        validatePurchaseAmountUnit(purchaseAmount);
        return purchaseAmount;
    }

    private Lotto tryInputWinningNumber() {
        String rawInput = inputView.inputWinningNumber();
        rawInput = InputPreprocessor.removeSpace(rawInput);
        validateLottoNumberSize(rawInput);
        validateLottoNumbersAreNumeric(rawInput);
        validateLottoNumberRange(rawInput);
        validateLottoNumberDuplication(rawInput);
        return parseLotto(rawInput);
    }

    private int tryInputBonusNumber(List<Integer> bannedNumbers) {
        String rawInput = inputView.inputBonusNumber();
        rawInput = InputPreprocessor.removeSpace(rawInput);
        validateBonusNumberIsNumeric(rawInput);
        validateBonusNumberIsRange(rawInput);
        validateBonusNumberBan(rawInput, bannedNumbers);
        return Integer.parseInt(rawInput);
    }

    private void validateLottoNumberIsNumeric(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new WrongInputException(InputExceptionMessage.LOTTO_NUMBER_RANGE_IS_WRONG.getMessage());
        }
    }

    private void validatePurchaseAmountUnit(int purchaseAmount) {
        if (purchaseAmount % LottoSetting.PRICE != DefaultValue.ZERO) {
            throw new WrongInputException(InputExceptionMessage.LOTTO_PURCHASE_AMOUNT_IS_WRONG.getMessage());
        }
    }

    private void validateLottoNumberSize(String rawInput) {
        List<String> rawNumbers = List.of(rawInput.split(InputSeparator.COMMA.getContent()));
        if (rawNumbers.size() != LottoSetting.NUMBER_COUNT) {
            throw new WrongInputException(InputExceptionMessage.LOTTO_NUMBER_COUNT_IS_WRONG.getMessage());
        }
    }

    private void validateLottoNumbersAreNumeric(String rawInput) {
        List<String> rawNumbers = List.of(rawInput.split(InputSeparator.COMMA.getContent()));
        rawNumbers.forEach(this::validateLottoNumberIsNumeric);
    }

    private void validateLottoNumberRange(String rawInput) {
        List<String> rawNumbers = List.of(rawInput.split(InputSeparator.COMMA.getContent()));
        for (String rawNumber : rawNumbers) {
            int number = Integer.parseInt(rawNumber);
            if (number < LottoSetting.MINIMUM_NUMBER || number > LottoSetting.MAXIMUM_NUMBER) {
                throw new WrongInputException(InputExceptionMessage.LOTTO_NUMBER_RANGE_IS_WRONG.getMessage());
            }
        }
    }

    private void validateLottoNumberDuplication(String rawInput) {
        List<String> rawNumbers = List.of(rawInput.split(InputSeparator.COMMA.getContent()));
        Set<Integer> numbers = rawNumbers.stream().map(Integer::parseInt).collect(Collectors.toSet());
        if (numbers.size() != rawNumbers.size()) {
            throw new WrongInputException(InputExceptionMessage.DUPLICATED_LOTTO_NUMBER_IS_NOT_ALLOWED.getMessage());
        }
    }

    private void validateBonusNumberIsNumeric(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new WrongInputException(InputExceptionMessage.BONUS_NUMBER_IS_NOT_NUMERIC.getMessage());
        }
    }

    private void validateBonusNumberIsRange(String input) {
        int number = Integer.parseInt(input);
        if (number < LottoSetting.MINIMUM_NUMBER || number > LottoSetting.MAXIMUM_NUMBER) {
            throw new WrongInputException(InputExceptionMessage.BONUS_NUMBER_RANGE_IS_NOT_ALLOWED.getMessage());
        }
    }

    private void validateBonusNumberBan(String input, List<Integer> bannedNumbers) {
        int number = Integer.parseInt(input);
        for (int bannedNumber : bannedNumbers) {
            if (bannedNumber == number) {
                throw new WrongInputException(
                        InputExceptionMessage.DUPLICATED_BONUS_NUMBER_IS_NOT_ALLOWED.getMessage());
            }
        }
    }


    private Lotto parseLotto(String rawInput) {
        List<String> rawNumbers = List.of(rawInput.split(InputSeparator.COMMA.getContent()));
        List<Integer> numbers = rawNumbers.stream().map(Integer::parseInt).toList();
        return new Lotto(numbers);
    }
}
