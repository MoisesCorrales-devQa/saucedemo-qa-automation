package pages.checkout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckOutCompletePage {

    private WebDriver driver;
    public CheckOutCompletePage(WebDriver driver) {
        this.driver = driver;
    }

    //SELECTORS
    private final By completeHeader = By.className("complete-header");
    private final By icon = By.cssSelector("[data-test='pony-express']");


    public boolean checkCompleteHeaderAndIcon() {
        WebElement title = driver.findElement(completeHeader);
        boolean correctText = title.getText().equals("Thank you for your order!");

        WebElement iconElement = driver.findElement(icon);
        boolean iconVisible = iconElement.isDisplayed();

        return correctText && iconVisible;
    }
}
