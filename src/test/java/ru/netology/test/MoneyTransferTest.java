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
    void shouldTransferMoneyFromSecondCardToFirstCard() {
        String amount = "100";
        val loginPage = new LoginPage();
        val authInfo = UserDataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val dashboardPage = verificationPage.validVerify(UserDataHelper.getVerificationCodeFor(authInfo));
        val firstCardStartBalance = dashboardPage.getFirstCardBalance();
        val secondCardStartBalance = dashboardPage.getSecondCardBalance();
        val moneyTransferPage = dashboardPage.firstCardClick();
        moneyTransferPage.transferMoney(UserDataHelper.getSecondCardNumber(), amount);
        val expectedAmountFirstCard = firstCardStartBalance + Integer.parseInt(amount);
        val expectedAmountSecondCard = secondCardStartBalance - Integer.parseInt(amount);
        val actualAmountFirstCard = dashboardPage.getFirstCardBalance();
        val actualAmountSecondCard = dashboardPage.getSecondCardBalance();
        assertEquals(expectedAmountFirstCard, actualAmountFirstCard);
        assertEquals(expectedAmountSecondCard, actualAmountSecondCard);
    }

    @Test
    void shouldTransferMoneyFromFirstCardToSecondCard() {
        String amount = "150";
        val loginPage = new LoginPage();
        val authInfo = UserDataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val dashboardPage = verificationPage.validVerify(UserDataHelper.getVerificationCodeFor(authInfo));
        val firstCardStartBalance = dashboardPage.getFirstCardBalance();
        val secondCardStartBalance = dashboardPage.getSecondCardBalance();
        val moneyTransferPage = dashboardPage.secondCardClick();
        moneyTransferPage.transferMoney(UserDataHelper.getFirstCardNumber(), amount);
        val expectedAmountFirstCard = firstCardStartBalance - Integer.parseInt(amount);
        val expectedAmountSecondCard = secondCardStartBalance + Integer.parseInt(amount);
        val actualAmountFirstCard = dashboardPage.getFirstCardBalance();
        val actualAmountSecondCard = dashboardPage.getSecondCardBalance();
        assertEquals(expectedAmountFirstCard, actualAmountFirstCard);
        assertEquals(expectedAmountSecondCard, actualAmountSecondCard);
    }

    @Test
    void shouldTransferMoneyFromFirstCardToFirstCard() {
        String amount = "300";
        val loginPage = new LoginPage();
        val authInfo = UserDataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val dashboardPage = verificationPage.validVerify(UserDataHelper.getVerificationCodeFor(authInfo));
        val firstCardStartBalance = dashboardPage.getFirstCardBalance();
        val secondCardStartBalance = dashboardPage.getSecondCardBalance();
        val moneyTransferPage = dashboardPage.firstCardClick();
        moneyTransferPage.transferMoney(UserDataHelper.getFirstCardNumber(), amount);
        val expectedAmountFirstCard = firstCardStartBalance;
        val expectedAmountSecondCard = secondCardStartBalance;
        val actualAmountFirstCard = dashboardPage.getFirstCardBalance();
        val actualAmountSecondCard = dashboardPage.getSecondCardBalance();
        assertEquals(expectedAmountFirstCard, actualAmountFirstCard);
        assertEquals(expectedAmountSecondCard, actualAmountSecondCard);
    }

    @Test
    void shouldTransferMoneyFromSecondCardToSecondCard() {
        String amount = "190";
        val loginPage = new LoginPage();
        val authInfo = UserDataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val dashboardPage = verificationPage.validVerify(UserDataHelper.getVerificationCodeFor(authInfo));
        val firstCardStartBalance = dashboardPage.getFirstCardBalance();
        val secondCardStartBalance = dashboardPage.getSecondCardBalance();
        val moneyTransferPage = dashboardPage.secondCardClick();
        moneyTransferPage.transferMoney(UserDataHelper.getSecondCardNumber(), amount);
        val expectedAmountFirstCard = firstCardStartBalance;
        val expectedAmountSecondCard = secondCardStartBalance;
        val actualAmountFirstCard = dashboardPage.getFirstCardBalance();
        val actualAmountSecondCard = dashboardPage.getSecondCardBalance();
        assertEquals(expectedAmountFirstCard, actualAmountFirstCard);
        assertEquals(expectedAmountSecondCard, actualAmountSecondCard);
    }

    @Test /*Bug*/
    void shouldNotTransferMoneyFromSecondCardToFirstCardIfNotEnoughMoney() {
        String amount = "21000";
        val loginPage = new LoginPage();
        val authInfo = UserDataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val dashboardPage = verificationPage.validVerify(UserDataHelper.getVerificationCodeFor(authInfo));
        val moneyTransferPage = dashboardPage.firstCardClick();
        moneyTransferPage.transferMoney(UserDataHelper.getSecondCardNumber(), amount);
        moneyTransferPage.assertErrorMsg();
    }

    @Test /*Bug*/
    void shouldNotTransferMoneyFromFirstCardToSecondCardIfNotEnoughMoney() {
        String amount = "21000";
        val loginPage = new LoginPage();
        val authInfo = UserDataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val dashboardPage = verificationPage.validVerify(UserDataHelper.getVerificationCodeFor(authInfo));
        val moneyTransferPage = dashboardPage.secondCardClick();
        moneyTransferPage.transferMoney(UserDataHelper.getFirstCardNumber(), amount);
        moneyTransferPage.assertErrorMsg();
    }
}