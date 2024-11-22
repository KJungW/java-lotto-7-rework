package lotto.utility;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class RandomNumberGenerator {

    public static List<Integer> makeRandomNumber(int startNumber, int endNumber, int count) {
        if (startNumber > endNumber) {
            throw new IllegalArgumentException("잘못된 랜덤번호 범위 설정입니다");
        }
        return Randoms.pickUniqueNumbersInRange(startNumber, endNumber, count);
    }

}
