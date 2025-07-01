package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import utils.BrowserInfo;
import utils.*;

public abstract class BaseTest {

    protected WebDriver driver;
    private static boolean environmentWritten = false;

    @BeforeEach
    void launchBrowser() {
        String browser = ConfigReader.get("browser");
        String baseUrl = ConfigReader.get("baseUrl");

        driver = DriverFactory.createInstance(browser);

        driver.manage().window().maximize();
        driver.get(baseUrl);

        if (!environmentWritten) {
            EnvironmentWriter.write(driver);
            environmentWritten = true;
        }
    }


    @AfterEach
    void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
