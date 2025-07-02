package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.VisualHelper;

import java.time.Duration;

public class ProductDetailPage {

    private WebDriver driver;
    public ProductDetailPage(WebDriver driver) {
        this.driver = driver;
    }

    //SELECTORS
    private final By itemTittle = By.cssSelector("[data-test='inventory-item-name']");
    private final By backToProducts = By.cssSelector("[data-test='back-to-products']");

    public boolean checkItemTitle(String itemName){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(itemTittle));
        return title.isDisplayed() && title.getText().equals(itemName);
    }

    public void clickBackToProducts() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement backToProductsBtn = wait.until(ExpectedConditions.elementToBeClickable(backToProducts));
        VisualHelper.highlight(driver, backToProductsBtn);
        backToProductsBtn.click();

        VisualHelper.pause(1000);
    }
}
