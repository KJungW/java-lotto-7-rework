package lotto.configuration;

import lotto.service.InputService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class ApplicationConfiguration {

    private final OutputView outputView
            = new OutputView();
    private final InputView inputView
            = new InputView();
    private final InputService inputService
            = new InputService(inputView, outputView);

    public InputService getInputService() {
        return inputService;
    }
}