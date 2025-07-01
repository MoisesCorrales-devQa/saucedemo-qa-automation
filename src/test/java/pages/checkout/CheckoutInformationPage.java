package pages.checkout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.VisualHelper;

public class CheckoutInformationPage {

    private WebDriver driver;
    public CheckoutInformationPage(WebDriver driver) {
        this.driver = driver;
    }

    //SELECTORS
    private final By firstNameSelector = By.cssSelector("input[data-test='firstName']");
    private final By lastNameelector = By.cssSelector("input[data-test='lastName']");
    private final By postalCodeSelector = By.cssSelector("input[data-test='postalCode']");
    private final By continueButton = By.cssSelector("input[data-test='continue']");


    public void fillCheckoutForm(String firstName, String lastName, String postalCode) {
        WebElement firstNameInput = driver.findElement(firstNameSelector);
        WebElement lastNameInput = driver.findElement(lastNameelector);
        WebElement postalCodeInput = driver.findElement(postalCodeSelector);

        VisualHelper.highlight(driver, firstNameInput);
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);
        VisualHelper.pause(1000);

        VisualHelper.highlight(driver, lastNameInput);
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);
        VisualHelper.pause(1000);

        VisualHelper.highlight(driver, postalCodeInput);
        postalCodeInput.clear();
        postalCodeInput.sendKeys(postalCode);
        VisualHelper.pause(1000);
    }

    public void clickContinueButton() {
        WebElement icon =  driver.findElement(continueButton);

        VisualHelper.highlight(driver, icon);
        icon.click();
        VisualHelper.pause(500);
    }




}
