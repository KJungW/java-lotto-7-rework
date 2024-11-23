package lotto.utility;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import lotto.constant.exception_message.UtilityExceptionMessage;

public class RandomNumberGenerator {

    public static List<Integer> makeRandomNumber(int startNumber, int endNumber, int count) {
        if (startNumber > endNumber) {
            throw new IllegalArgumentException(UtilityExceptionMessage.RANDOM_NUMBER_RANGE_IS_WRONG.getMessage());
        }
        return Randoms.pickUniqueNumbersInRange(startNumber, endNumber, count);
    }

}
