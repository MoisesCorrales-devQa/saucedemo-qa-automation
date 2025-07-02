package pages;

import com.google.errorprone.annotations.OverridingMethodsMustInvokeSuper;
import model.CartItem;
import model.ProductItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ActionsHelper;
import utils.VisualHelper;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class AboutPage {

    private WebDriver driver;
    public AboutPage(WebDriver driver) {
        this.driver = driver;
    }

    //SELECTORS
    private final By pageTitle = By.cssSelector(".MuiTypography-root.MuiTypography-h1.css-152qxt");

    public boolean isTittleDisplayed() {

        String titleText = "Build apps users love with AI-driven insights";
        return ActionsHelper.isVisibleWithText(driver, pageTitle, 5, titleText);

    }

}