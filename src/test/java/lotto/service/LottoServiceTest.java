package lotto.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.constant.WinningType;
import lotto.domain.Lotto;
import lotto.repository.LottoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoServiceTest {

    private LottoService lottoService;
    private LottoRepository lottoRepository;

    @BeforeEach
    void beforeEach() {
        lottoRepository = new LottoRepository();
        lottoService = new LottoService(lottoRepository);
    }

    @DisplayName("로또 발행 - 정상흐름")
    @Test
    void 로또_발행_정상흐름() {
        int purchasePrice = 5000;

        lottoService.issueLottos(purchasePrice);

        assertThat(lottoRepository.findAll().size()).isEqualTo(5);
    }

    @DisplayName("당첨 계산 - 정상흐름")
    @Test
    void 당첨_계산_정상흐름() {
        List<Lotto> lottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(2, 3, 4, 5, 6, 7)),
                new Lotto(List.of(13, 14, 15, 16, 17, 18)));
        lottoRepository.replaceAll(lottos);

        List<WinningType> winningTypes = lottoService.calculateWinning(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);

        assertThat(winningTypes).containsExactly(WinningType.FIRST, WinningType.SECOND);
    }

    @DisplayName("당첨 계산 - 당첨되지 않았을 경우")
    @Test
    void 당첨_계산_당첨되지_않았을_경우() {
        List<Lotto> lottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(2, 3, 4, 5, 6, 7)),
                new Lotto(List.of(13, 14, 15, 16, 17, 18)));
        lottoRepository.replaceAll(lottos);

        List<WinningType> winningTypes = lottoService.calculateWinning(new Lotto(List.of(45, 43, 42, 41, 40, 39)), 38);

        assertThat(winningTypes).isEmpty();
    }

    @DisplayName("수익률 계산 - 정상흐름")
    @Test
    void 수익률_계산_정상흐름() {
        int purchaseAmount = 8000;
        List<WinningType> winningTypes = List.of(WinningType.FIFTH);

        double rateOfReturn = lottoService.calculateRateOfReturn(purchaseAmount, winningTypes);

        assertThat(rateOfReturn).isEqualTo(62.5d);
    }
}