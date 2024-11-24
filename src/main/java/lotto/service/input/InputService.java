package lotto.service.input;

import java.util.List;
import lotto.constant.exception_message.InputExceptionMessage;
import lotto.domain.Lotto;
import lotto.exception.WrongInputException;
import lotto.utility.InputPreprocessor;
import lotto.view.InputView;
import lotto.view.OutputView;

public class InputService {

    private final InputView inputView;
    private final OutputView outputView;
    private final InputValidationService validationService;
    private final InputParsingService parsingService;

    public InputService(InputView inputView, OutputView outputView, InputValidationService validationService,
            InputParsingService parsingService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.validationService = validationService;
        this.parsingService = parsingService;
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
        validationService.validateLottoNumberIsNumeric(rawInput);
        int purchaseAmount = Integer.parseInt(rawInput);
        validationService.validatePurchaseAmountUnit(purchaseAmount);
        return purchaseAmount;
    }

    private Lotto tryInputWinningNumber() {
        String rawInput = inputView.inputWinningNumber();
        rawInput = InputPreprocessor.removeSpace(rawInput);
        validationService.validateLottoNumberCount(rawInput);
        validationService.validateLottoNumbersAreNumeric(rawInput);
        validationService.validateLottoNumberRange(rawInput);
        validationService.validateLottoNumberDuplication(rawInput);
        return parsingService.parseLotto(rawInput);
    }

    private int tryInputBonusNumber(List<Integer> bannedNumbers) {
        String rawInput = inputView.inputBonusNumber();
        rawInput = InputPreprocessor.removeSpace(rawInput);
        validationService.validateBonusNumberIsNumeric(rawInput);
        validationService.validateBonusNumberIsRange(rawInput);
        validationService.validateBonusNumberBan(rawInput, bannedNumbers);
        return Integer.parseInt(rawInput);
    }
}
