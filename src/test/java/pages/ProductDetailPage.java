package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ActionsHelper;
import utils.VisualHelper;

import java.time.Duration;

public class ProductDetailPage {

    private WebDriver driver;
    public ProductDetailPage(WebDriver driver) {
        this.driver = driver;
    }

    //SELECTORS
    private final By itemTitle = By.cssSelector("[data-test='inventory-item-name']");
    private final By backToProducts = By.cssSelector("[data-test='back-to-products']");

    public boolean checkItemTitle(String itemName){
        return ActionsHelper.isVisibleWithText(driver, itemTitle, 10, itemName);
    }

    public void clickBackToProducts() {
        ActionsHelper.click(driver, backToProducts);
    }
}
