package lotto.controller;

import java.util.List;
import lotto.configuration.ApplicationConfiguration;
import lotto.constant.WinningType;
import lotto.domain.Lotto;
import lotto.service.LottoService;
import lotto.service.OutputService;
import lotto.service.input.InputService;

public class LottoController {

    private final InputService inputService;
    private final OutputService outputService;
    private final LottoService lottoService;

    public LottoController(ApplicationConfiguration configuration) {
        inputService = configuration.getInputService();
        outputService = configuration.getOutputService();
        lottoService = configuration.getLottoService();
    }

    public void run() {
        int priceAmount = inputService.inputPurchaseAmount();
        lottoService.issueLottos(priceAmount);
        outputService.printIssuedLotto();
        Lotto winningLotto = inputService.inputWinningNumber();
        int bonusNumber = inputService.inputBonusNumber(winningLotto.getNumbers());
        List<WinningType> winningTypes = lottoService.calculateWinning(winningLotto, bonusNumber);
        double rateOfReturn = lottoService.calculateRateOfReturn(priceAmount, winningTypes);
        outputService.printWinningResult(winningTypes);
        outputService.printRateOfReturn(rateOfReturn);
    }
}
