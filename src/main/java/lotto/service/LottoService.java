package lotto.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.constant.WinningType;
import lotto.domain.Lotto;
import lotto.repository.LottoRepository;
import lotto.utility.RandomNumberGenerator;

public class LottoService {

    private final LottoRepository lottoRepository;

    public LottoService(LottoRepository lottoRepository) {
        this.lottoRepository = lottoRepository;
    }

    public void issueLottos(int priceAmount) {
        int issuedLottoCount = priceAmount / 1000;
        List<Lotto> issuedLottos = new ArrayList<>();
        for (int i = 0; i < issuedLottoCount; i++) {
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

    private Lotto makeLotto() {
        Set<Integer> numbers = new HashSet<>();
        for (int i = 0; i < 6; i++) {
            addLottoNumber(numbers);
        }
        return new Lotto(numbers.stream().toList());
    }

    private void addLottoNumber(Set<Integer> numbers) {
        while (true) {
            int newNumber = RandomNumberGenerator.makeRandomNumber(1, 45);
            if (!numbers.contains(newNumber)) {
                numbers.add(newNumber);
                return;
            }
        }
    }
}
