package lotto.controller;

import lotto.configuration.ApplicationConfiguration;
import lotto.service.InputService;

public class LottoController {

    private final InputService inputService;

    public LottoController(ApplicationConfiguration configuration) {
        inputService = configuration.getInputService();
    }

    public void run() {
        int priceAmount = inputService.inputPurchaseAmount();
    }
}
