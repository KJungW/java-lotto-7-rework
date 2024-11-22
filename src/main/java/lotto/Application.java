package lotto;

import lotto.configuration.ApplicationConfiguration;
import lotto.controller.LottoController;

public class Application {

    public static void main(String[] args) {
        ApplicationConfiguration configuration = new ApplicationConfiguration();
        LottoController controller = new LottoController(configuration);
        controller.run();
    }
}