package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.VisualHelper;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement userField = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
        VisualHelper.highlight(driver, userField);
        userField.sendKeys(username);
        VisualHelper.pause(500);

        WebElement passField = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        VisualHelper.highlight(driver, passField);
        passField.sendKeys(password);
        VisualHelper.pause(500);

        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        VisualHelper.highlight(driver, loginBtn);
        loginBtn.click();

        VisualHelper.pause(1000); // espera tras login
    }

    public boolean isInvalidUserErrorVisible() {
        return isErrorTextVisible(INVALID_USER);
    }

    public boolean isLockedUserErrorVisible() {
        return isErrorTextVisible(LOCKED_USER);
    }

    public boolean isNoUserErrorVisible() {
        return isErrorTextVisible(NO_USER);
    }

    public boolean isNoPsswdErrorVisible() {
        return isErrorTextVisible(NO_PSSWD);
    }

    public boolean isErrorTextVisible(String errorText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        return title.getText().equals(errorText);
    }

}

