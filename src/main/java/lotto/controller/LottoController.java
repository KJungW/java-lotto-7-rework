package lotto.controller;

import lotto.configuration.ApplicationConfiguration;
import lotto.service.InputService;
import lotto.service.LottoService;

public class LottoController {

    private final InputService inputService;
    private final LottoService lottoService;

    public LottoController(ApplicationConfiguration configuration) {
        inputService = configuration.getInputService();
        lottoService = configuration.getLottoService();
    }

    public void run() {
        int priceAmount = inputService.inputPurchaseAmount();
        lottoService.issueLottos(priceAmount);
    }
}
