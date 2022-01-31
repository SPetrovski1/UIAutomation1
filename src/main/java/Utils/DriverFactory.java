package Utils;

import Enums.DriverTypeEnum;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    private final static String GOOGLE_CHROME_SET_UP_NAME = "webdriver.chrome.driver";
    private final static String GOOGLE_CHROME_SET_UP_PATH = "src/main/resources/Drivers/chromedriver.exe";

    private final static String FIREFOX_SET_UP_NAME = "webdriver.gecko.driver";
    private final static String FIREFOX_SET_UP_PATH = "src/main/resources/Drivers/geckodriver.exe";

    public static WebDriver createDriverForBrowserWithValue(DriverTypeEnum driverType) {
        String driverVersion = PropertiesReader.readFromProperties(ConfigurationConstants.MY_PROPERTIES_PATH,
            ConfigurationConstants.DRIVER_TYPE_VERSION);
        WebDriver driver = null;
        if (driverType.equals(DriverTypeEnum.GOOGLE_CHROME_DRIVER)) {
            ChromeDriverManager.getInstance().version(driverVersion).setup();
            driver = new ChromeDriver();
        }
        if (driverType.equals(DriverTypeEnum.GOOGLE_CHROME_DRIVER_HEADLESS)) {
            ChromeDriverManager.getInstance().version(driverVersion).setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("window-size=1920x1080");
            chromeOptions.addArguments("--disable-gpu");
            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("--allow-insecure-localhost");
            driver = new ChromeDriver(chromeOptions);
        }
        if (driverType.equals(DriverTypeEnum.FIRE_FOX_DRIVER)) {
            FirefoxDriverManager.getInstance().version(driverVersion).setup();
            driver = new FirefoxDriver();
        }
        if (driver == null) {
            throw new RuntimeException("The driver wasn't initialised");
        }
        driver.manage().window().maximize();
        return driver;
    }
}
