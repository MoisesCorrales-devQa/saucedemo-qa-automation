package tests;

import model.ProductItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;
import pages.checkout.CheckOutCompletePage;
import pages.checkout.CheckoutInformationPage;
import pages.checkout.CheckoutOverviewPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckOutTests extends BaseTest {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutInformationPage checkoutInformationPage;
    private CheckoutOverviewPage checkoutOverviewPage;
    private CheckOutCompletePage checkoutCompletePage;

    //TEST DATA
    private final String name = "Name";
    private final String lastName = "LastName";
    private final String postCode = "12345";


    @BeforeEach
    void setUp() {
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checkoutInformationPage = new CheckoutInformationPage(driver);
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        checkoutCompletePage = new CheckOutCompletePage(driver);

        loginPage.loginAs("standard_user", "secret_sauce");
    }

    @Test
    @DisplayName("TC012 - Flujo de compra completo (E2E)")
    void TC012_flujo_de_compra_completo() {

        //PRECONDTION: Añadir item al carro

        ProductItem producto = inventoryPage.addItemsToCart(1).get(0);

        //STEP 1. Clic en carrito

        inventoryPage.navigateToCart();

        //STEP 2. Iniciar checkout

        cartPage.startCheckoutProcess();

        //STEP 3. Completar datos

        checkoutInformationPage.fillCheckoutForm(name, lastName, postCode);
        checkoutInformationPage.clickContinueButton();

        //STEP 4. Confirmar compra

        checkoutOverviewPage.clickFinishButton();

        //SPECTED_RESULT: Página muestra mensaje de confirmación de orden

        boolean isHeaderVisible = checkoutCompletePage.checkCompleteHeaderAndIcon();
        assertTrue(isHeaderVisible, "No se muestra la pantalla de checkout completado");

    }



}
