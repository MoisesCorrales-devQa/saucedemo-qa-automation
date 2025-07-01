package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ItemDetailPage {

    private WebDriver driver;
    public ItemDetailPage(WebDriver driver) {
        this.driver = driver;
    }

    //SELECTORS
    private final By itemTittle = By.cssSelector("[data-test='inventory-item-name']");

    public boolean checkItemTitle(String itemName){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(itemTittle));
        return title.isDisplayed() && title.getText().equals(itemName);
    }

}
