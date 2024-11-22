package lotto.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
