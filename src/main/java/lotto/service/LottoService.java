package lotto.service;

import java.util.ArrayList;
import java.util.List;
import lotto.constant.WinningType;
import lotto.domain.Lotto;
import lotto.repository.LottoRepository;
import lotto.utility.RandomNumberGenerator;

public class LottoService {

    private final LottoRepository lottoRepository;

    public LottoService(LottoRepository lottoRepository) {
        this.lottoRepository = lottoRepository;
    }

    public void issueLottos(int purchaseAmount) {
        int issueCount = purchaseAmount / 1000;
        List<Lotto> issuedLottos = new ArrayList<>();
        for (int i = 0; i < issueCount; i++) {
            issuedLottos.add(makeLotto());
        }
        lottoRepository.replaceAll(issuedLottos);
    }

    public List<Lotto> findIssuedLottos() {
        return lottoRepository.findAll();
    }

    public List<WinningType> calculateWinning(Lotto winningLotto, int bonusNumber) {
        List<Lotto> issuedLotto = lottoRepository.findAll();
        List<WinningType> winningTypes = issuedLotto.stream().map(lotto -> {
            int matchedNumberCount = lotto.calculateMatchedNumberCount(winningLotto);
            boolean isBonusNumberMatched = lotto.checkBonusNumberMatching(bonusNumber);
            return WinningType.findWinningType(matchedNumberCount, isBonusNumberMatched);
        }).toList();
        return winningTypes.stream().filter(type -> !type.equals(WinningType.NONE)).toList();
    }

    public double calculateRateOfReturn(int purchaseAmount, List<WinningType> winningTypes) {
        long totalPrize = winningTypes.stream().mapToLong(WinningType::getPrize).sum();
        double rateOfReturn = (totalPrize / (double) purchaseAmount) * 100;
        return (int) (rateOfReturn * 100) / (double) 100;
    }

    private Lotto makeLotto() {
        List<Integer> numbers = RandomNumberGenerator.makeRandomNumber(1, 45, 6);
        return new Lotto(numbers);
    }
}
