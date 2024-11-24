package lotto.service.input;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lotto.constant.DefaultValue;
import lotto.constant.InputSeparator;
import lotto.constant.LottoSetting;
import lotto.constant.exception_message.InputExceptionMessage;
import lotto.exception.WrongInputException;

public class InputValidationService {

    public void validatePurchasePriceIsNumeric(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new WrongInputException(InputExceptionMessage.LOTTO_PURCHASE_AMOUNT_IS_NOT_NUMERIC.getMessage());
        }
    }

    public void validatePurchaseAmountUnit(int purchaseAmount) {
        if (purchaseAmount % LottoSetting.PRICE != DefaultValue.ZERO) {
            throw new WrongInputException(InputExceptionMessage.LOTTO_PURCHASE_AMOUNT_IS_WRONG.getMessage());
        }
    }

    public void validateLottoNumberIsNumeric(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new WrongInputException(InputExceptionMessage.LOTTO_NUMBER_RANGE_IS_WRONG.getMessage());
        }
    }

    public void validateLottoNumbersAreNumeric(String rawInput) {
        List<String> rawNumbers = List.of(rawInput.split(InputSeparator.COMMA.getContent()));
        rawNumbers.forEach(this::validateLottoNumberIsNumeric);
    }

    public void validateLottoNumberCount(String rawInput) {
        List<String> rawNumbers = List.of(rawInput.split(InputSeparator.COMMA.getContent()));
        if (rawNumbers.size() != LottoSetting.NUMBER_COUNT) {
            throw new WrongInputException(InputExceptionMessage.LOTTO_NUMBER_COUNT_IS_WRONG.getMessage());
        }
    }

    public void validateLottoNumberRange(String rawInput) {
        List<String> rawNumbers = List.of(rawInput.split(InputSeparator.COMMA.getContent()));
        for (String rawNumber : rawNumbers) {
            int number = Integer.parseInt(rawNumber);
            if (number < LottoSetting.MINIMUM_NUMBER || number > LottoSetting.MAXIMUM_NUMBER) {
                throw new WrongInputException(InputExceptionMessage.LOTTO_NUMBER_RANGE_IS_WRONG.getMessage());
            }
        }
    }

    public void validateLottoNumberDuplication(String rawInput) {
        List<String> rawNumbers = List.of(rawInput.split(InputSeparator.COMMA.getContent()));
        Set<Integer> numbers = rawNumbers.stream().map(Integer::parseInt).collect(Collectors.toSet());
        if (numbers.size() != rawNumbers.size()) {
            throw new WrongInputException(InputExceptionMessage.DUPLICATED_LOTTO_NUMBER_IS_NOT_ALLOWED.getMessage());
        }
    }

    public void validateBonusNumberIsNumeric(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new WrongInputException(InputExceptionMessage.BONUS_NUMBER_IS_NOT_NUMERIC.getMessage());
        }
    }

    public void validateBonusNumberIsRange(String input) {
        int number = Integer.parseInt(input);
        if (number < LottoSetting.MINIMUM_NUMBER || number > LottoSetting.MAXIMUM_NUMBER) {
            throw new WrongInputException(InputExceptionMessage.BONUS_NUMBER_RANGE_IS_NOT_ALLOWED.getMessage());
        }
    }

    public void validateBonusNumberBan(String input, List<Integer> bannedNumbers) {
        int number = Integer.parseInt(input);
        for (int bannedNumber : bannedNumbers) {
            if (bannedNumber == number) {
                throw new WrongInputException(
                        InputExceptionMessage.DUPLICATED_BONUS_NUMBER_IS_NOT_ALLOWED.getMessage());
            }
        }
    }
}
