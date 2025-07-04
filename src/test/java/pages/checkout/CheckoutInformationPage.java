package pages.checkout;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ActionsHelper;

import java.time.Duration;

public class CheckoutInformationPage {

    private WebDriver driver;
    public CheckoutInformationPage(WebDriver driver) {
        this.driver = driver;
    }

    //SELECTORS
    private final By firstNameSelector = By.cssSelector("input[data-test='firstName']");
    private final By lastNameSelector = By.cssSelector("input[data-test='lastName']");
    private final By postalCodeSelector = By.cssSelector("input[data-test='postalCode']");
    private final By continueButton = By.cssSelector("input[data-test='continue']");
    private final By cancelButton = By.id("cancel");

    private final By errorSelector = By.cssSelector("input[data-test='postalCode']");
    private final By subtotalSelector = By.cssSelector(".summary_subtotal_label[data-test='subtotal-label']");
    private final By taxSelector = By.cssSelector(".summary_tax_label[data-test='tax-label']");
    private final By totalPriceSelector = By.cssSelector(".summary_total_label[data-test='total-label']");


    //CONSTANTS
    private final String NO_NAME_ERROR = "Error: First Name is required";
    private final String NO_LASTNAME_ERROR = "Error: Last Name is required";
    private final String NO_ZIPCODE_ERROR = "Error: Postal Code is required";

    public void fillCheckoutForm(String firstName, String lastName, String postalCode) {
        ActionsHelper.sendKeys(driver, firstNameSelector, firstName, 10);
        ActionsHelper.sendKeys(driver, lastNameSelector, lastName, 10);
        ActionsHelper.sendKeys(driver, postalCodeSelector, postalCode, 10);
    }

    public void clickContinueButton() {
        ActionsHelper.click(driver, continueButton);
    }


    public void checkNameErrorMessage() {
        ActionsHelper.isVisibleWithText(driver, errorSelector, NO_NAME_ERROR, 10);
    }

    public void checkLastNameErrorMessage() {
        ActionsHelper.isVisibleWithText(driver, errorSelector, NO_LASTNAME_ERROR, 10);
    }

    public void checkZipCodeErrorMessage() {
        ActionsHelper.isVisibleWithText(driver, errorSelector, NO_ZIPCODE_ERROR, 10);
    }

    public void clickCancelButton() {
        ActionsHelper.click(driver, cancelButton);
    }

    public boolean checkPriceWithTaxes(double productsPrice) {

        double price = extractDoublePrice(subtotalSelector,"Item total: $");
        double tax = extractDoublePrice(taxSelector,"Tax: $");
        double total = extractDoublePrice(totalPriceSelector,"Total: $");

        boolean validateSubtotal = productsPrice == price;
        boolean validateTotal = price + tax == total;

        return validateSubtotal && validateTotal;

    }

    private double extractDoublePrice(By selector, String replace) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(selector));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        String textValue = element.getText();
        String formattedValue = textValue.replace(replace, "");

        return Double.parseDouble(formattedValue);
    }
}
