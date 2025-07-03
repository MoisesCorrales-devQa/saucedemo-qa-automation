package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.ActionsHelper;

public class AboutPage {

    private WebDriver driver;
    public AboutPage(WebDriver driver) {
        this.driver = driver;
    }

    //SELECTORS
    private final By pageTitle = By.cssSelector(".MuiTypography-root.MuiTypography-h1.css-152qxt");

    public boolean isTittleDisplayed() {

        String titleText = "Build apps users love with AI-driven insights";
        return ActionsHelper.isVisibleWithText(driver, pageTitle, titleText, 5);

    }

}