package lotto.repository;

import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lotto;

public class LottoRepository {

    private List<Lotto> lottos;

    public void replaceAll(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<Lotto> findAll() {
        return lottos.stream()
                .map(Lotto::copy)
                .collect(Collectors.toList());
    }
}
