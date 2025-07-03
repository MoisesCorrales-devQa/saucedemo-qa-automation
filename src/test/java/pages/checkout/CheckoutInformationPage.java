package pages.checkout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ActionsHelper;

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

}
