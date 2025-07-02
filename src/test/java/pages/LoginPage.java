package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ActionsHelper;
import utils.VisualHelper;

public class LoginPage {

    private WebDriver driver;

    //Selectors
    private By usernameInput = By.id("user-name");
    private By passwordInput = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.cssSelector("[data-test='error']");

    //Error Messages
    private String INVALID_USER = "Epic sadface: Username and password do not match any user in this service";
    private String LOCKED_USER = "Epic sadface: Sorry, this user has been locked out.";
    private String NO_USER = "Epic sadface: Username is required";
    private String NO_PSSWD = "Epic sadface: Password is required";

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void loginAs(String username, String password) {
        ActionsHelper.sendKeys(driver, usernameInput, username, 10);

        ActionsHelper.sendKeys(driver, passwordInput, password, 10);

        ActionsHelper.click(driver, loginButton);

        VisualHelper.pause(1000); // espera tras login
    }

    public boolean isInvalidUserErrorVisible() {
        return ActionsHelper.isVisibleWithText(driver, errorMessage, 10, INVALID_USER);
    }

    public boolean isLockedUserErrorVisible() {
        return ActionsHelper.isVisibleWithText(driver, errorMessage, 10, LOCKED_USER);
    }

    public boolean isNoUserErrorVisible() {
        return ActionsHelper.isVisibleWithText(driver, errorMessage, 10, NO_USER);
    }

    public boolean isNoPsswdErrorVisible() {
        return ActionsHelper.isVisibleWithText(driver, errorMessage, 10, NO_PSSWD);
    }

    public boolean isTittleDisplayed() {

        boolean isUserInputVisible = ActionsHelper.isVisible(driver, usernameInput,10);
        boolean isPasswdInputVisible = ActionsHelper.isVisible(driver, passwordInput,10);
        boolean isLoginButtonVisible = ActionsHelper.isVisible(driver, loginButton,10);

        return isUserInputVisible && isPasswdInputVisible && isLoginButtonVisible;
    }
}

