package lotto.service;

import java.text.DecimalFormat;
import java.util.List;
import lotto.constant.WinningType;
import lotto.constant.input_output_message.OutputMessage;
import lotto.data_transfer_object.WinningCondition;
import lotto.domain.Lotto;
import lotto.view.OutputView;

public class OutputService {

    private final OutputView outputView;
    private final LottoService lottoService;

    public OutputService(OutputView outputView, LottoService lottoService) {
        this.outputView = outputView;
        this.lottoService = lottoService;
    }

    public void printIssuedLotto() {
        List<Lotto> issuedLottos = lottoService.findIssuedLottos();
        String content = String.format(OutputMessage.ISSUED_LOTTO_COUNT.getMessage(), issuedLottos.size());
        outputView.printContent(content);
        issuedLottos.forEach(this::printLotto);
    }

    public void printWinningResult(List<WinningType> winningTypes) {
        outputView.printContent(OutputMessage.WINNING_RESULT_INTRODUCTION_START.getMessage());
        List<WinningType> allWinningTypes = List.of(WinningType.values()).reversed();
        for (WinningType type : allWinningTypes) {
            WinningCondition condition = type.getWinningCondition();
            String bonusNumberMatchingState = makeBonusNumberMatchingState(condition.isBonusNumberMatched());
            int typeCount = countWinningType(type, winningTypes);
            String content = String.format(OutputMessage.WINNING_RESULT_CONTENT.getMessage(),
                    condition.getMatchedNumberCount(), bonusNumberMatchingState, type.getPrize(), typeCount);
            outputView.printContent(content);
        }
    }

    public void printRateOfReturn(double rateOfReturn) {
        DecimalFormat decimalFormat = new DecimalFormat(OutputMessage.RATE_OF_RETURN_FORMAT.getMessage());
        String rateText = decimalFormat.format(rateOfReturn) + OutputMessage.PERCENT_SIGN.getMessage();
        String content = String.format(OutputMessage.RATE_OF_RETURN_CONTENT.getMessage(), rateText);
        outputView.printContent(content);
    }

    private void printLotto(Lotto lotto) {
        List<Integer> numbers = lotto.getNumbers();
        String content = makeNumberContent(numbers);
        outputView.printContent(content);
    }

    private String makeNumberContent(List<Integer> numbers) {
        List<String> numberTexts = numbers.stream().map(String::valueOf).toList();
        String content = String.join(OutputMessage.LOTTO_NUMBER_SEPARATOR.getMessage(), numberTexts);
        return String.format(OutputMessage.SQUARE_BRACKETS.getMessage(), content);
    }

    private String makeBonusNumberMatchingState(boolean isMatching) {
        if (isMatching) {
            return OutputMessage.MATCHED_BONUS_NUMBER_CONTENT.getMessage();
        }
        return OutputMessage.SPACE.getMessage();
    }

    private int countWinningType(WinningType standardType, List<WinningType> targetTypes) {
        return targetTypes.stream()
                .filter(type -> type.equals(standardType))
                .toList()
                .size();
    }
}
