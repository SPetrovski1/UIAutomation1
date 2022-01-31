package AccountTests;

import Models.AccountModel;
import Page.AccountSummaryPage;
import Page.HomePage;
import Page.LoginPage;
import Utils.BaseTestClass;
import Utils.ConfigurationConstants;
import java.util.Optional;
import org.testng.annotations.Test;


public class accountTest extends BaseTestClass {

    private HomePage homePage;
    private LoginPage loginPage;
    private AccountSummaryPage accountSummaryPage;


    @Override
    protected void beforeClassExtended() {
        homePage = new HomePage(driver);
        homePage = (HomePage) homePage.navigateTo(baseUrl, homePage);
        loginPage = homePage.clickBtnSingIn();
        accountSummaryPage = loginPage.loginWithCredentials(ConfigurationConstants.USERNAME_COURSE,
            ConfigurationConstants.PASSWORD_COURSE);

    }

    @Test(priority = 1)
    public void checkCashAccountsState() {
        accountSummaryPage.readCashAccounts();
        Optional<AccountModel> actualAccountModel =
            accountSummaryPage.findAccountByName("Savings", accountSummaryPage.getAccountModelListCash());

        softAssert.assertTrue(actualAccountModel.isPresent(), "The account was not present");
        softAssert.assertTrue(actualAccountModel.get().getBalance().contains("$1000"), "Account balance not ok");
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void checkInvestmentAccountsState() {
        accountSummaryPage.readInvestments();
        Optional<AccountModel> actualAccountModel =
            accountSummaryPage.findAccountByName("Brokerage", accountSummaryPage.getAccountModelListInvestment());

        softAssert.assertTrue(actualAccountModel.isPresent(), "The account was not present");
        softAssert.assertTrue(actualAccountModel.get().getBalance().contains("$197"), "Account balance not ok");
        softAssert.assertAll();
    }

    @Test(priority = 3)
    public void checkCreditAccountsState() {
        accountSummaryPage.readCredits();
        Optional<AccountModel> actualAccountModel =
            accountSummaryPage.findAccountByName("Checking", accountSummaryPage.getAccountModelListCredit());

        softAssert.assertTrue(actualAccountModel.isPresent(), "The account was not present");
        softAssert.assertTrue(actualAccountModel.get().getBalance().contains("$-500.2"), "Account balance not ok");
        softAssert.assertAll();
    }

    @Test(priority = 4)
    public void checkLoanAccountState() {
        accountSummaryPage.readLoans();
        Optional<AccountModel> actualAccountModel =
            accountSummaryPage.findAccountByName("Loan", accountSummaryPage.getAccountModelListLoan());

        softAssert.assertTrue(actualAccountModel.isPresent(), "The account was not present");
        softAssert.assertTrue(actualAccountModel.get().getBalance().contains("$780"), "Account balance not ok");
        softAssert.assertAll();
    }
}
