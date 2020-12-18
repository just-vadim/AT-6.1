package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.UserDataHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {

    @BeforeEach
    void setupAll() {
        open("http://localhost:9999/");
    }

    @Test
    void shouldTransferMoneyFromSecondCard2FirstCard() {
        int amount = 100;
        val loginPage = new LoginPage();
        val authInfo = UserDataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val dashboardPage = verificationPage.validVerify(UserDataHelper.getVerificationCodeFor(authInfo));
        val firstCardStartBalance = dashboardPage.getFirstCardBalance();
        val secondCardStartBalance = dashboardPage.getSecondCardBalance();
        val moneyTransferPage = dashboardPage.firstCardClick();
        moneyTransferPage.transferMoney(UserDataHelper.getSecondCardNumber(), amount);
        val expectedAmountFirstCard = firstCardStartBalance + amount;
        val expectedAmountSecondCard = secondCardStartBalance - amount;
        val actualAmountFirstCard = dashboardPage.getFirstCardBalance();
        val actualAmountSecondCard = dashboardPage.getSecondCardBalance();
        assertEquals(expectedAmountFirstCard, actualAmountFirstCard);
        assertEquals(expectedAmountSecondCard, actualAmountSecondCard);
    }
}