package lotto.constant;

import java.util.List;
import lotto.data_transfer_object.WinningCondition;

public enum WinningType {
    FIRST(new WinningCondition(6, false), 2000000000),
    SECOND(new WinningCondition(5, true), 30000000),
    THIRD(new WinningCondition(5, false), 1500000),
    FOURTH(new WinningCondition(4, false), 50000),
    FIFTH(new WinningCondition(3, false), 5000),
    NONE(new WinningCondition(0, false), 0);

    private final WinningCondition winningCondition;
    private final long prize;

    WinningType(WinningCondition winningCondition, long prize) {
        this.winningCondition = winningCondition;
        this.prize = prize;
    }

    public static WinningType findWinningType(int matchedNumberCount, boolean isBonusNumberMatched) {
        List<WinningType> allTypes = List.of(WinningType.values());
        for (WinningType type : allTypes) {
            boolean isWinning = type.getWinningCondition().checkWinning(matchedNumberCount, isBonusNumberMatched);
            if (isWinning) {
                return type;
            }
        }
        return WinningType.NONE;
    }

    public WinningCondition getWinningCondition() {
        return winningCondition;
    }

    public long getPrize() {
        return prize;
    }
}
