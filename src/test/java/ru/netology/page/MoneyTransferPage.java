package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.UserDataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class MoneyTransferPage {
    private SelenideElement amountField = $("[data-test-id='amount'] input.input__control");
    private SelenideElement fromField = $("[data-test-id='from'] input.input__control");

    public MoneyTransferPage transferMoney(UserDataHelper.CardInfo cardInfo, String amount) {
        amountField.setValue(amount);
        fromField.setValue(cardInfo.getCardNumber());
        $("[data-test-id='action-transfer']").click();
        return new MoneyTransferPage();
    }

    public void assertErrorMsg() {
        $("[data-test-id='error-notification'] .notification__content")
                .waitUntil(Condition.visible, 5000)
                .shouldHave(text("Произошла ошибка"));
    }
}