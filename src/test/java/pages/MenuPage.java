package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ActionsHelper;

import java.time.Duration;

public class MenuPage {

    private WebDriver driver;
    public MenuPage(WebDriver driver) {
        this.driver = driver;
    }

    //SELECTORS
    private final By menuLateral = By.cssSelector(".bm-menu");
    private final By iconoMenuLateral = By.className("bm-burger-button");
    private final By closeButton = By.id("react-burger-cross-btn");
    private final By inventoryLink = By.id("inventory_sidebar_link");
    private final By aboutLink = By.id("about_sidebar_link");
    private final By logOutLink = By.id("logout_sidebar_link");
    private final By resetLink = By.id("reset_sidebar_link");

    public void openMenu() {
        ActionsHelper.click(driver, iconoMenuLateral);
    }

    public boolean isMenuVisible() {
        return ActionsHelper.isVisible(driver, menuLateral, 2);
    }

    public boolean isMenuInvisible() {
        return ActionsHelper.waitForInvisibility(driver, menuLateral, 3);
    }

    public void closeMenu() {
        ActionsHelper.click(driver, closeButton);
    }

    public void clickAllItems() {
        ActionsHelper.click(driver, inventoryLink);
    }

    public void clickAbout() {
        ActionsHelper.click(driver, aboutLink);
    }

    public void clickLogout() {
        ActionsHelper.click(driver, logOutLink);
    }

    public void clickResetAppState() {
        ActionsHelper.click(driver, resetLink);
    }
}
