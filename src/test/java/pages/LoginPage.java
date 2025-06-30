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

    private By usernameInput = By.id("user-name");
    private By passwordInput = By.id("password");
    private By loginButton = By.id("login-button");

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

    public boolean checkTittlePage(){
        WebElement title = driver.findElement(By.cssSelector("[data-test='title']"));
        return title.getText().equals("Products");
    }
}

