package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InventoryPage {
    private WebDriver driver;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isTitleVisible() {
        WebElement title = driver.findElement(By.cssSelector("[data-test='title']"));
        return title.isDisplayed() && title.getText().equals("Products");
    }
}
