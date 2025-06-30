package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.DriverFactory;

public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeAll
    static void setupDriverManager() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void launchBrowser() {
        driver = DriverFactory.createInstance("chrome");
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @AfterEach
    void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
