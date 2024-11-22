package lotto.utility;

import camp.nextstep.edu.missionutils.Randoms;

public class RandomNumberGenerator {

    public static int makeRandomNumber(int startNumber, int endNumber) {
        if (startNumber > endNumber) {
            throw new IllegalArgumentException("잘못된 랜덤번호 범위 설정입니다");
        }
        return Randoms.pickNumberInRange(startNumber, endNumber);
    }

}
