package lotto.controller;

import lotto.configuration.ApplicationConfiguration;
import lotto.domain.Lotto;
import lotto.service.InputService;
import lotto.service.LottoService;
import lotto.service.OutputService;

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
    }
}
