package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.UserDataHelper;

import static com.codeborne.selenide.Selenide.$;

public class MoneyTransferPage {
    private SelenideElement amountField = $("[data-test-id='amount'] input.input__control");
    private SelenideElement fromField = $("[data-test-id='from'] input.input__control");

    public MoneyTransferPage transferMoney(UserDataHelper.CardInfo cardInfo, int amount) {
        amountField.setValue(String.valueOf(amount));
        fromField.setValue(cardInfo.getCardNumber());
        $("[data-test-id='action-transfer']").click();
        return new MoneyTransferPage();
    }
}