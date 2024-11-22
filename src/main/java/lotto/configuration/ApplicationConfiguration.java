package lotto.configuration;

import lotto.repository.LottoRepository;
import lotto.service.InputService;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class ApplicationConfiguration {

    private final OutputView outputView
            = new OutputView();
    private final InputView inputView
            = new InputView();
    private final InputService inputService
            = new InputService(inputView, outputView);
    private final LottoRepository lottoRepository
            = new LottoRepository();
    private final LottoService lottoService
            = new LottoService(lottoRepository);

    public InputService getInputService() {
        return inputService;
    }

    public LottoService getLottoService() {
        return lottoService;
    }
}