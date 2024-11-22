package lotto.data_transfer_object;

public class WinningCondition {

    private final int matchedNumberCount;
    private final boolean isBonusNumberMatched;

    public WinningCondition(int matchedNumberCount, boolean isBonusNumberMatched) {
        this.matchedNumberCount = matchedNumberCount;
        this.isBonusNumberMatched = isBonusNumberMatched;
    }

    public boolean checkWinning(int matchedNumberCount, boolean isBonusNumberMatched) {
        return this.matchedNumberCount == matchedNumberCount &&
                this.isBonusNumberMatched == isBonusNumberMatched;
    }
}
