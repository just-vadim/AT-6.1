package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.apache.commons.lang3.StringUtils;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id='dashboard']");
    private ElementsCollection cardList = $$(".list__item");
    private final SelenideElement firstCardButton = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] .button");
    private final SelenideElement secondCardButton = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] .button");

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public int getBalance(String value) {
        String stringBalance = StringUtils.substringBetween(value, ":", "р");
        return Integer.parseInt(stringBalance.trim());
    }

    public int getFirstCardBalance() {
        String balance = cardList.first().getText();
        return getBalance(balance);
    }

    public int getSecondCardBalance() {
        String balance = cardList.last().getText();
        return getBalance(balance);
    }

    public MoneyTransferPage firstCardClick() {
        firstCardButton.click();
        return new MoneyTransferPage();
    }

    public MoneyTransferPage secondCardClick() {
        secondCardButton.click();
        return new MoneyTransferPage();
    }
}