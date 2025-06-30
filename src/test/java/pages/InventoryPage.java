package pages;

import model.ProductItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.VisualHelper;

import java.util.ArrayList;
import java.util.List;

public class InventoryPage {
    private WebDriver driver;

    //Selectors
    private By orderSelector = By.cssSelector("select[data-test='product-sort-container']");
    private By inventoryTittle = By.cssSelector("[data-test='title']");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isTitleVisible() {
        WebElement title = driver.findElement(inventoryTittle);
        return title.isDisplayed() && title.getText().equals("Products");
    }

    public void selectOrder(String criterioValor) {
        WebElement dropdown = driver.findElement(By.cssSelector("select[data-test='product-sort-container']"));
        VisualHelper.highlight(driver, dropdown); // opcional, para ver el efecto
        Select select = new Select(dropdown);
        select.selectByValue(criterioValor); // ejemplo: "az", "za", "lohi", "hilo"
    }

    public List<ProductItem> getVisibleProducts() {
        List<WebElement> items = driver.findElements(By.className("inventory_item"));
        List<ProductItem> products = new ArrayList<>();

        for (WebElement item : items) {
            VisualHelper.highlight(driver, item);

            String name = item.findElement(By.className("inventory_item_name")).getText();
            String description = item.findElement(By.className("inventory_item_desc")).getText();
            String price = item.findElement(By.className("inventory_item_price")).getText();

            products.add(new ProductItem(name, description, price));

            VisualHelper.pause(1000);
        }
        return products;
    }
}
