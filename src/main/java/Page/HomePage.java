package Page;

import Utils.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(id = "signin_button")
    private WebElement btnSignIn;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BasePage newInstance(WebDriver driver) {
        return new HomePage(driver);
    }

    public LoginPage clickBtnSingIn() {
        btnSignIn.click();
        return new LoginPage(getDriver());
    }
}
