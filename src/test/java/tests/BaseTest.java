package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;
import utils.DriverFactory;

public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeEach
    void launchBrowser() {
        String browser = ConfigReader.get("browser");
        String baseUrl = ConfigReader.get("baseUrl");

        driver = DriverFactory.createInstance(browser);
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }

    @AfterEach
    void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
