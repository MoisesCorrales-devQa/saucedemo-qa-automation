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
    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    //Selectors
    private final By orderSelector = By.cssSelector("select[data-test='product-sort-container']");
    private final By inventoryTittle = By.cssSelector("[data-test='title']");

    private final By inventoryItem = By.className("inventory_item");
    private final By itemName = By.className("inventory_item_name");
    private final By itemDesc = By.className("inventory_item_desc");
    private final By itemPrice = By.className("inventory_item_price");

    private final By button = By.cssSelector("button");
    private final By badge = By.cssSelector("[data-test='shopping-cart-badge']");
    private final By cartIcon = By.cssSelector("[data-test='shopping-cart-link']");

    public boolean isTitleVisible() {
        WebElement title = driver.findElement(inventoryTittle);
        return title.isDisplayed() && title.getText().equals("Products");
    }

    public void selectOrder(String criterioValor) {
        WebElement dropdown = driver.findElement(orderSelector);
        VisualHelper.highlight(driver, dropdown); // opcional, para ver el efecto
        Select select = new Select(dropdown);
        select.selectByValue(criterioValor); // ejemplo: "az", "za", "lohi", "hilo"
    }

    public List<ProductItem> getVisibleProducts() {
        List<WebElement> items = driver.findElements(inventoryItem);
        List<ProductItem> products = new ArrayList<>();

        for (WebElement item : items) {
            VisualHelper.highlight(driver, item);

            String name = item.findElement(itemName).getText();
            String description = item.findElement(itemDesc).getText();
            String price = item.findElement(itemPrice).getText();

            products.add(new ProductItem(name, description, price));

            VisualHelper.pause(1000);
        }
        return products;
    }

    public List<ProductItem> addItemsToCart(int amount) {
        List<WebElement> items = driver.findElements(inventoryItem);
        List<ProductItem> addedItems = new ArrayList<>();

        int limit = Math.min(amount, items.size());

        for (int i = 0; i < limit; i++) {
            WebElement item = items.get(i);
            VisualHelper.highlight(driver, item);

            String name = item.findElement(itemName).getText();
            String description = item.findElement(itemDesc).getText();
            String price = item.findElement(itemPrice).getText();

            addedItems.add(new ProductItem(name, description, price));

            WebElement button = item.findElement(this.button);
            button.click();

            VisualHelper.pause(500);
        }
        return addedItems;
    }

    public boolean checkCartIconBadge(int amount) {

        WebElement badge = driver.findElement(this.badge);
        String badgeText = badge.getText();
        int itemCount = Integer.parseInt(badgeText);

        return itemCount == amount;
    }

    public void navigateToCart() {
        WebElement icon =  driver.findElement(cartIcon);

        VisualHelper.highlight(driver, icon);
        icon.click();
        VisualHelper.pause(500);
    }

}
