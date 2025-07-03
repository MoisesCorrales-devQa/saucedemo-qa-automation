package pages.checkout;

import model.CartItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ActionsHelper;
import utils.VisualHelper;

import java.util.ArrayList;
import java.util.List;

public class CheckoutOverviewPage {

    private WebDriver driver;
    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
    }

    //SELECTORS
    private final By cartItem = By.className("cart_item");
    private final By cartItemName = By.className("inventory_item_name");
    private final By cartItemDesc = By.className("inventory_item_desc");
    private final By cartItemPrice = By.className("inventory_item_price");
    private final By cartItemQty = By.className("cart_quantity");

    private final By finishButtonSelector = By.id("finish");
    private final By cancelButtonSelector = By.id("cancel");


    public List<CartItem> getOverviewItems() {
        List<WebElement> items = driver.findElements(cartItem);
        List<CartItem> cartItems = new ArrayList<>();

        for (WebElement item : items) {
            VisualHelper.highlight(driver, item);

            String name = item.findElement(cartItemName).getText();
            String desc = item.findElement(cartItemDesc).getText();
            String price = item.findElement(cartItemPrice).getText();
            int qty = Integer.parseInt(item.findElement(cartItemQty).getText());

            cartItems.add(new CartItem(name, desc, price, qty));

            VisualHelper.pause(1000);
        }

        return cartItems;
    }

    public void clickFinishButton() {
        ActionsHelper.click(driver, finishButtonSelector);
    }

    public void clickCancelButton() {
        ActionsHelper.click(driver, cancelButtonSelector);
    }

}
