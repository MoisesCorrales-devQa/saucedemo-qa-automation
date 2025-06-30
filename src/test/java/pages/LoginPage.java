package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.VisualHelper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginPage {

    private WebDriver driver;

    //Selectors
    private By usernameInput = By.id("user-name");
    private By passwordInput = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.cssSelector("[data-test='error']");

    //Errormessages
    private String INVALID_USER = "Epic sadface: Username and password do not match any user in this service";
    private String LOCKED_USER = "Epic sadface: Sorry, this user has been locked out.";


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void loginAs(String username, String password) {
        WebElement userField = driver.findElement(usernameInput);
        VisualHelper.highlight(driver, userField);
        userField.sendKeys(username);
        VisualHelper.pause(500);

        WebElement passField = driver.findElement(passwordInput);
        VisualHelper.highlight(driver, passField);
        passField.sendKeys(password);
        VisualHelper.pause(500);

        WebElement loginBtn = driver.findElement(loginButton);
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

    public boolean isErrorTextVisible(String errorText) {
        WebElement title = driver.findElement(errorMessage);
        System.out.println(title.getText());

        return title.getText().equals(errorText);
    }

}

