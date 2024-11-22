package lotto.service;

import java.text.DecimalFormat;
import java.util.List;
import lotto.constant.WinningType;
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
        outputView.printContent(issuedLottos.size() + "개를 구매했습니다.");
        issuedLottos.forEach(this::printLotto);
    }

    public void printWinningResult(List<WinningType> winningTypes) {
        outputView.printContent("당첨 통계");
        outputView.printContent("---");
        List<WinningType> allWinningTypes = List.of(WinningType.values()).reversed();
        for (WinningType type : allWinningTypes) {
            WinningCondition condition = type.getWinningCondition();
            String bonusNumberMatchingState = makeBonusNumberMatchingState(condition.isBonusNumberMatched());
            int typeCount = countWinningType(type, winningTypes);
            String content = String.format("%d개 일치%s(%,d원) - %d개",
                    condition.getMatchedNumberCount(), bonusNumberMatchingState, type.getPrize(), typeCount);
            outputView.printContent(content);
        }
    }

    public void printRateOfReturn(double rateOfReturn) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String rateText = decimalFormat.format(rateOfReturn);
        String content = String.format("총 수익률은 %s입니다.", rateText + "%");
        outputView.printContent(content);
    }

    private void printLotto(Lotto lotto) {
        List<Integer> numbers = lotto.getNumbers();
        String content = makeNumberContent(numbers);
        outputView.printContent(content);
    }

    private String makeNumberContent(List<Integer> numbers) {
        StringBuilder content = new StringBuilder();
        content.append("[");
        for (int i = 0; i < numbers.size(); i++) {
            content.append(numbers.get(i));
            if (i != numbers.size() - 1) {
                content.append(", ");
            }
        }
        return content.append("]").toString();
    }

    private String makeBonusNumberMatchingState(boolean isMatching) {
        if (isMatching) {
            return ", 보너스 볼 일치 ";
        }
        return " ";
    }

    private int countWinningType(WinningType standardType, List<WinningType> targetTypes) {
        return targetTypes.stream()
                .filter(type -> type.equals(standardType))
                .toList()
                .size();
    }
}
