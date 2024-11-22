package lotto.service;

import java.util.List;
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
}
