package pages;

import model.ProductItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ActionsHelper;
import utils.VisualHelper;

import java.time.Duration;
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
    private final By itemIcon = By.className("inventory_item_img");


    private final By button = By.cssSelector("button");
    private final By badge = By.cssSelector("[data-test='shopping-cart-badge']");
    private final By cartIcon = By.cssSelector("[data-test='shopping-cart-link']");

    public boolean isTitleDisplayed() {

        String titleText = "Products";
        return ActionsHelper.isVisibleWithText(driver, inventoryTittle, titleText, 10);
    }


    public void selectOrder(String criterioValor) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(orderSelector));
        VisualHelper.highlight(driver, dropdown);
        Select select = new Select(dropdown);
        select.selectByValue(criterioValor);
    }

    public List<ProductItem> getVisibleProducts() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(inventoryItem));
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
        return ActionsHelper.isVisibleWithText(driver, this.badge, String.valueOf(amount), 10);
    }

    public boolean isCartIconBadgeVisible() {
        return ActionsHelper.isVisible(driver, this.badge,3);
    }

    public void navigateToCart() {
        ActionsHelper.click(driver, cartIcon);
    }

    private String navigateToItem(int position, By clickableSelector) {
        WebElement item = driver.findElements(inventoryItem).get(position);
        VisualHelper.highlight(driver, item);

        WebElement clickableElement = item.findElement(clickableSelector);
        String name = item.findElement(itemName).getText();

        VisualHelper.pause(500);
        clickableElement.click();

        VisualHelper.pause(500);
        return name;
    }

    public String navigateToItemFromImage(int position) {
        return navigateToItem(position, itemIcon);
    }

    public String navigateToItemFromTittle(int position) {
        return navigateToItem(position, itemName);
    }
}
