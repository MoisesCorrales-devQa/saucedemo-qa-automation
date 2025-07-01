package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
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

@Epic("Checkout")
@Feature("Flujo de Compra")
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

    @Story("Flujo de compra completo (E2E)")
    @Description("""
        TC012 - Flujo de compra completo (E2E)
        Precondición: Producto en el carrito
        Pasos:
        1. Clic en carrito
        2. Iniciar checkout
        3. Completar datos
        4. Confirmar compra
        Datos: Nombre, Apellido, Código Postal
        Resultado esperado: Página muestra mensaje de confirmación de orden
        """)
    @Test
    @DisplayName("TC012 - Flujo de compra completo (E2E)")
    void TC012_flujo_de_compra_completo() {

        ProductItem producto = inventoryPage.addItemsToCart(1).get(0);

        inventoryPage.navigateToCart();

        cartPage.startCheckoutProcess();

        checkoutInformationPage.fillCheckoutForm(name, lastName, postCode);
        checkoutInformationPage.clickContinueButton();

        checkoutOverviewPage.clickFinishButton();

        boolean isHeaderVisible = checkoutCompletePage.checkCompleteHeaderAndIcon();
        assertTrue(isHeaderVisible, "No se muestra la pantalla de checkout completado");
    }

    @Story("Validación de campos requeridos en checkout")
    @Description("""
        TC013 - Validación de campos requeridos en checkout
        Precondición: Producto en el carrito
        Pasos:
        1. Iniciar checkout
        2. Dejar campos vacíos
        3. Clic en continuar
        Datos: (vacíos)
        Resultado esperado: Mensajes de error visibles por campo
        """)
    //@Test
    @DisplayName("TC013 - Validación de campos requeridos en checkout")
    void TC013_validacionCamposCheckout() {
        // Implementación de la prueba
    }

    @Story("Navegación entre pasos de checkout")
    @Description("""
        TC014 - Navegación entre pasos de checkout
        Precondición: Producto en el carrito
        Pasos:
        1. Iniciar checkout
        2. Continuar
        3. Volver al carrito / atrás
        Datos: -
        Resultado esperado: El sistema navega correctamente sin errores
        """)
    //@Test
    @DisplayName("TC014 - Navegación entre pasos de checkout")
    void TC014_navegacionPasosCheckout() {
        // Implementación de la prueba
    }

    @Story("Verificar productos en Checkout Overview")
    @Description("""
        TC015 - Verificar productos en Checkout Overview
        Precondición: Producto en el carrito
        Pasos:
        1. Iniciar checkout
        2. Completar datos
        3. Continuar a Overview
        Datos: Varios ítems
        Resultado esperado: Todos los productos añadidos aparecen con nombre y precio correcto
        """)

    //@Test
    @DisplayName("TC015 - Verificar productos en Checkout Overview")
    void TC015_verificarProductosCheckoutOverview() {
        // Implementación de la prueba
    }

    @Story("Verificar total con impuestos en Checkout Overview")
    @Description("""
        TC016 - Verificar total con impuestos en Checkout Overview
        Precondición: Producto en el carrito
        Pasos:
        1. Iniciar checkout
        2. Completar datos
        3. Continuar a Overview
        Datos: Varios ítems
        Resultado esperado: El subtotal es la suma de precios; el impuesto es fijo (ej. $2.40); el total es correcto
        """)
    //@Test
    @DisplayName("TC016 - Verificar total con impuestos en Checkout Overview")
    void TC016_verificarTotalCheckoutOverview() {
        // Implementación de la prueba
    }

}
