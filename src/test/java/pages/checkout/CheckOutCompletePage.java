package pages.checkout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckOutCompletePage {

    private WebDriver driver;
    public CheckOutCompletePage(WebDriver driver) {
        this.driver = driver;
    }

    //SELECTORS
    private final By completeHeader = By.className("complete-header");
    private final By icon = By.cssSelector("[data-test='pony-express']");


    public boolean checkCompleteHeaderAndIcon() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(completeHeader));
        boolean correctText = title.getText().equals("Thank you for your order!");
        WebElement iconElement = wait.until(ExpectedConditions.visibilityOfElementLocated(icon));
        boolean iconVisible = iconElement.isDisplayed();
        return correctText && iconVisible;
    }
}
