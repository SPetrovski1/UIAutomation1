import io.github.bonigarcia.wdm.ChromeDriverManager;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FirstTest {

    private WebDriver driver;

    @BeforeTest
    public void beforeTest(){
        ChromeDriverManager.getInstance().version("2.40").setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver,10);
        driver.get("http://zero.webappsecurity.com/");
    }

    @Test
    private void firstTest(){
        WebElement signInBtn = driver.findElement(By.id("signin_button"));
        Actions actions = new Actions(driver);
        actions.moveToElement(signInBtn).perform();
        signInBtn.click();
        WebElement userInput = driver.findElement(By.id("user_login"));
        userInput.sendKeys("username");
        WebElement passwordInput = driver.findElement(By.id("user_password"));
        passwordInput.sendKeys("password");
        WebElement signInButton = driver.findElement(By.className("btn-primary"));
        actions.moveToElement(signInButton).perform();
        signInButton.click();

    }

    @Test
    private void secondTest(){

        WebElement signInBtn = driver.findElement(By.id("signin_button"));
        Actions actions = new Actions(driver);
        actions.moveToElement(signInBtn).perform();
        signInBtn.click();
        WebElement userInput = driver.findElement(By.id("user_login"));
        userInput.sendKeys("username");
        WebElement passwordInput = driver.findElement(By.id("user_password"));
        passwordInput.sendKeys("password");
        WebElement signInButton = driver.findElement(By.className("btn-primary"));
        actions.moveToElement(signInButton).perform();
        signInButton.click();
        Assert.assertEquals(1,1);

    }

    @AfterTest
    public void afterTest(){
        driver.close();
        driver.quit();
    }
}
