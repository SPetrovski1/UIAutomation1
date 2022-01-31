package Utils;

import java.util.List;
import java.util.Optional;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


@Getter
public abstract class BasePage {

    private WebDriver driver;
    private WebDriverWait wait;
    private AjaxElementLocatorFactory factory;
    private Actions actions;
    private JavascriptExecutor javascriptExecutor;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.factory =
            new AjaxElementLocatorFactory(driver, ConfigurationConstants.MAX_RETRY_FOR_LOCATING_ELEMENT_AJAX_FACTORY);
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, ConfigurationConstants.MAX_RETRY_FOR_LOCATING_ELEMENT);
        actions = new Actions(driver);
        javascriptExecutor = (JavascriptExecutor) driver;
    }

    protected void moveToElement(WebElement element) {
        actions.moveToElement(element);
        actions.perform();
    }

    public abstract BasePage newInstance(WebDriver driver);

    public <T extends BasePage> BasePage navigateTo(String url, T type) {
        driver.get(url);
        return type.newInstance(driver);
    }

    protected void clearAndSendKeys(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        moveToElement(element);
        element.clear();
        element.sendKeys(text);
    }

    protected void waitForElementToBeClickableAndClick(WebElement elem) {
        moveToElement(elem);
        wait.until(ExpectedConditions.elementToBeClickable(elem));
        elem.click();
    }

    protected WebElement waitAndFindElement(WebElement root, By byLocator) {
        wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(root, byLocator));
        return root.findElement(byLocator);
    }

    protected WebElement waitAndFindElementFromRoot(By byLocator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
        return driver.findElement(byLocator);
    }

    protected List<WebElement> waitAndFindElementsFromRoot(By byLocator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
        return driver.findElements(byLocator);
    }

    protected List<WebElement> waitAndFindElements(WebElement root, By byLocator) {
        wait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(root, byLocator));
        return root.findElements(byLocator);
    }

    protected Optional<WebElement> checkAndGetIfElementIsPresent(WebElement root, By locator) {
        try {
            return Optional.of(root.findElement(locator));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    protected Optional<WebElement> checkAndGetIfElementIsPresent(By locator) {
        try {
            return Optional.of(driver.findElement(locator));
        } catch (Exception e) {
            return Optional.empty();
        }
    }


}
