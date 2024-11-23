package lotto.configuration;

import lotto.repository.LottoRepository;
import lotto.service.LottoService;
import lotto.service.OutputService;
import lotto.service.input.InputParsingService;
import lotto.service.input.InputService;
import lotto.service.input.InputValidationService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class ApplicationConfiguration {

    private final OutputView outputView
            = new OutputView();
    private final InputView inputView
            = new InputView();
    private final InputValidationService inputValidationService
            = new InputValidationService();
    private final InputParsingService inputParsingService
            = new InputParsingService();
    private final InputService inputService
            = new InputService(inputView, outputView, inputValidationService, inputParsingService);
    private final LottoRepository lottoRepository
            = new LottoRepository();
    private final LottoService lottoService
            = new LottoService(lottoRepository);
    private final OutputService outputService
            = new OutputService(outputView, lottoService);

    public InputService getInputService() {
        return inputService;
    }

    public LottoService getLottoService() {
        return lottoService;
    }

    public OutputService getOutputService() {
        return outputService;
    }
}