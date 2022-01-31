package Page;

import Models.AccountModel;
import Utils.BasePage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class AccountSummaryPage extends BasePage {

    private List<AccountModel> accountModelListCash;
    private List<AccountModel> accountModelListCredit;
    private List<AccountModel> accountModelListInvestment;
    private List<AccountModel> accountModelListLoan;

    @FindBy(className = "table")
    private List<WebElement> tableContainers;

    public AccountSummaryPage(WebDriver driver) {
        super(driver);
        accountModelListCash = new ArrayList<>();
        accountModelListCredit = new ArrayList<>();
        accountModelListInvestment = new ArrayList<>();
        accountModelListLoan = new ArrayList<>();
    }

    @Override
    public BasePage newInstance(WebDriver driver) {
        return new AccountSummaryPage(driver);
    }

    public void readCashAccounts() {
        accountModelListCash = readAccountModelTable(tableContainers.get(0));
    }

    public void readCredits() {
        accountModelListCredit = readAccountModelTable(tableContainers.get(2));
    }

    public void readInvestments() {
        accountModelListInvestment = readAccountModelTable(tableContainers.get(1));
    }

    public void readLoans() {
        accountModelListLoan = readAccountModelTable(tableContainers.get(3));
    }

    private List<AccountModel> readAccountModelTable(WebElement table) {
        List<AccountModel> accountModelList = new ArrayList<>();
        WebElement tableBody = waitAndFindElement(table, By.tagName("tbody"));
        List<WebElement> tableRows = waitAndFindElements(table, By.tagName("tr"));
        for (WebElement tableRow : tableRows) {
            accountModelList.add(readAccountModelRow(tableRow));
        }
        return accountModelList;
    }

    private AccountModel readAccountModelRow(WebElement tableRow) {
        Optional<WebElement> accountColumn = findColumnByLocator(tableRow, By.cssSelector("td:nth-child(1)"));
        Optional<WebElement> creditCardColumn = findColumnByLocator(tableRow, By.cssSelector("td:nth-child(2)"));
        Optional<WebElement> balanceColumn = findColumnByLocator(tableRow, By.cssSelector("td:nth-child(3)"));
        return AccountModel.builder()
            .accountName(
                accountColumn.isPresent() ? waitAndFindElement(accountColumn.get(), By.tagName("a")).getText() : null)
            .creditCardNumber(creditCardColumn.isPresent() ? creditCardColumn.get().getText() : null)
            .balance(balanceColumn.isPresent() ? balanceColumn.get().getText() : null)
            .build();

    }

    private Optional<WebElement> findColumnByLocator(WebElement row, By by) { //find an element within the row
        return checkAndGetIfElementIsPresent(row, by);
    }

    public Optional<AccountModel> findAccountByName(String accountName, List<AccountModel> accountModelList) {
        return accountModelList.stream().filter(accountModel -> accountName.equals(accountModel.getAccountName()))
            .findFirst();
    }
}
