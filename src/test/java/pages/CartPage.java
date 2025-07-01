package pages;

import com.google.errorprone.annotations.OverridingMethodsMustInvokeSuper;
import model.CartItem;
import model.ProductItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.VisualHelper;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CartPage {

    private WebDriver driver;
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    //SELECTORS
    private final By cartTitle = By.cssSelector("[data-test='title']");

    private final By cartItem = By.className("cart_item");
    private final By cartItemName = By.className("inventory_item_name");
    private final By cartItemDesc = By.className("inventory_item_desc");
    private final By cartItemPrice = By.className("inventory_item_price");
    private final By cartItemQty = By.className("cart_quantity");

    private final By button = By.cssSelector("button");
    private final By checkout = By.id("checkout");

    public List<CartItem> getCartItems() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartItem));
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

    public void removeItemFromCart(int position) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartItem));
        List<WebElement> items = driver.findElements(cartItem);
        WebElement item = items.get(position);
        VisualHelper.highlight(driver, item);
        WebElement button = item.findElement(this.button);
        wait.until(ExpectedConditions.elementToBeClickable(button)).click();

        VisualHelper.pause(500);
    }

    public void startCheckoutProcess() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(this.checkout));
        VisualHelper.highlight(driver, checkoutBtn);
        checkoutBtn.click();

        VisualHelper.pause(500);
    }

    public boolean isTitleVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(cartTitle));
        return title.isDisplayed() && title.getText().equals("Your Cart");
    }
}
