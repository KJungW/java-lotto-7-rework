package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.constant.input_output_message.InputGuideMessage;

public class InputView {

    public String inputPurchaseAmount() {
        System.out.println(InputGuideMessage.INPUT_PURCHASE_AMOUNT.getMessage());
        return Console.readLine();
    }

    public String inputWinningNumber() {
        System.out.println(InputGuideMessage.INPUT_WINNING_NUMBERS.getMessage());
        return Console.readLine();
    }

    public String inputBonusNumber() {
        System.out.println(InputGuideMessage.INPUT_BONUS_NUMBER.getMessage());
        return Console.readLine();
    }
}
