package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import model.CartItem;
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

import java.util.List;

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

        inventoryPage.addItemsToCart(1);
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
    TC013.1 - Validación campo requerido: Nombre
    Precondición: Producto en el carrito
    Pasos:
    1. Iniciar checkout
    2. Dejar campo 'Nombre' vacío
    3. Completar Apellido y Código Postal
    4. Clic en continuar
    Datos: Nombre vacío, Apellido válido, Código Postal válido
    Resultado esperado: Mensaje de error visible en campo 'Nombre'
    """)
    @Test
    @DisplayName("TC013.1 - Validación campo requerido: Nombre")
    void TC013_1_validacionCampoNombre() {
        inventoryPage.addItemsToCart(1);
        inventoryPage.navigateToCart();

        cartPage.startCheckoutProcess();

        checkoutInformationPage.clickContinueButton();

        checkoutInformationPage.checkNameErrorMessage();

    }

    @Story("Validación de campos requeridos en checkout")
    @Description("""
    TC013.2 - Validación campo requerido: Apellido
    Precondición: Producto en el carrito
    Pasos:
    1. Iniciar checkout
    2. Dejar campo 'Apellido' vacío
    3. Completar Nombre y Código Postal
    4. Clic en continuar
    Datos: Nombre válido, Apellido vacío, Código Postal válido
    Resultado esperado: Mensaje de error visible en campo 'Apellido'
    """)
    @Test
    @DisplayName("TC013.2 - Validación campo requerido: Apellido")
    void TC013_2_validacionCampoApellido() {
        inventoryPage.addItemsToCart(1);
        inventoryPage.navigateToCart();

        cartPage.startCheckoutProcess();

        checkoutInformationPage.fillCheckoutForm(name, "", "");

        checkoutInformationPage.clickContinueButton();

        checkoutInformationPage.checkLastNameErrorMessage();
    }

    @Story("Validación de campos requeridos en checkout")
    @Description("""
    TC013.3 - Validación campo requerido: Código Postal
    Precondición: Producto en el carrito
    Pasos:
    1. Iniciar checkout
    2. Dejar campo 'Código Postal' vacío
    3. Completar Nombre y Apellido
    4. Clic en continuar
    Datos: Nombre válido, Apellido válido, Código Postal vacío
    Resultado esperado: Mensaje de error visible en campo 'Código Postal'
    """)
    @Test
    @DisplayName("TC013.3 - Validación campo requerido: Código Postal")
    void TC013_3_validacionCampoCodigoPostal() {
        inventoryPage.addItemsToCart(1);
        inventoryPage.navigateToCart();

        cartPage.startCheckoutProcess();

        checkoutInformationPage.fillCheckoutForm(name, lastName, "");

        checkoutInformationPage.clickContinueButton();

        checkoutInformationPage.checkZipCodeErrorMessage();
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
    @Test
    @DisplayName("TC015 - Verificar productos en Checkout Overview")
    void TC015_verificarProductosCheckoutOverview() {

        List<ProductItem> addedProducts = inventoryPage.addItemsToCart(1);
        inventoryPage.navigateToCart();

        cartPage.startCheckoutProcess();

        checkoutInformationPage.fillCheckoutForm(name, lastName, postCode);

        checkoutInformationPage.clickContinueButton();

        List<CartItem> overviewProducts = checkoutOverviewPage.getOverviewItems();

        verificarItemsEnOverview(addedProducts, overviewProducts);

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
    @Test
    @DisplayName("TC016 - Verificar total con impuestos en Checkout Overview")
    void TC016_verificarTotalCheckoutOverview() {

        List<ProductItem> addedProducts =  inventoryPage.addItemsToCart(3);

        inventoryPage.navigateToCart();

        cartPage.startCheckoutProcess();

        checkoutInformationPage.fillCheckoutForm(name, lastName, postCode);

        checkoutInformationPage.clickContinueButton();

        double productPrices = 0;

        for (ProductItem product : addedProducts){
            String formattedValue = product.getPrice().replace("$", "");

            double precio = Double.parseDouble(formattedValue);

            productPrices += precio;
        }

        boolean valdiatePrices = checkoutInformationPage.checkPriceWithTaxes(productPrices);

        assertTrue(valdiatePrices, "Los precios no se calculan o muestran correctamente");

    }

    private void verificarItemsEnOverview(List<ProductItem> expectedItems, List<CartItem> actualItems) {
        assertEquals(expectedItems.size(), actualItems.size(), "El número de productos no coincide");

        for (int i = 0; i < actualItems.size(); i++) {
            ProductItem expected = expectedItems.get(i);
            CartItem actual = actualItems.get(i);

            assertEquals(expected.getName(), actual.getName(), "El nombre no coincide");
            assertEquals(expected.getDescription(), actual.getDescription(), "La descripción no coincide");
            assertEquals(expected.getPrice(), actual.getPrice(), "El precio no coincide");
            assertTrue(actual.getPrice().matches("\\$\\d+\\.\\d{2}"), "El precio no tiene el formato correcto: " + actual.getPrice());
            assertEquals(1, actual.getQuantity(), "La cantidad esperada es 1");
        }
    }

}
