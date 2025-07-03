package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.InventoryPage;
import pages.ProductDetailPage;
import pages.LoginPage;
import pages.checkout.CheckOutCompletePage;
import pages.checkout.CheckoutInformationPage;
import pages.checkout.CheckoutOverviewPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Navegación")
@Feature("Transiciones entre pantallas")
public class NavigationTests extends BaseTest {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private ProductDetailPage productDetailPage;
    private CheckoutInformationPage checkoutInformationPage;
    private CheckoutOverviewPage checkoutOverviewPage;

    //TEST DATA
    private final String name = "Name";
    private final String lastName = "LastName";
    private final String postCode = "12345";

    @BeforeEach
    void setUp() {
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        productDetailPage = new ProductDetailPage(driver);
        checkoutInformationPage = new CheckoutInformationPage(driver);
        checkoutOverviewPage = new CheckoutOverviewPage(driver);

        loginPage.loginAs("standard_user", "secret_sauce");
    }

    @Story("Navegar al carrito desde inventario")
    @Description("""
        TC020 - Navegar al carrito desde inventario
        Precondición: Usuario autenticado
        Pasos:
        1. Acceder al inventario
        2. Clic en el icono del carrito
        Datos: -
        Resultado esperado: Redirección a página del carrito
        """)
    @Test
    @DisplayName("TC020 - Navegar al carrito desde inventario")
    void TC020_navegarCarritoDesdeInventario() {

        inventoryPage.navigateToCart();

        assertTrue(driver.getCurrentUrl().contains("cart"), "No se redirigió al carrito");
        assertTrue(cartPage.isTitleDisplayed(), "El título del carrito no es visible");
    }

    @Story("Volver al inventario desde el carrito")
    @Description("""
        TC021 - Volver al inventario desde el carrito
        Precondición: Usuario en pantalla del carrito
        Pasos:
        1. Clic en botón "Continue Shopping"
        Datos: -
        Resultado esperado: Usuario vuelve a la página de inventario
        """)
    @Test
    @DisplayName("TC021 - Volver al inventario desde el carrito")
    void TC021_volverInventarioDesdeCarrito() {

        inventoryPage.navigateToCart();

        cartPage.clickOnGoToInventory();

        assertTrue(driver.getCurrentUrl().contains("inventory"), "No se redirigió a la pantalla de productos");
        assertTrue(inventoryPage.isTitleDisplayed(), "No se muestra la pantalla de productos correctamente");
    }

    @Story("Navegar a la pantalla de detalle de un producto (clic en nombre)")
    @Description("""
        TC022.1 - Navegar al detalle de un producto haciendo clic en el nombre
        Precondición: Usuario autenticado
        Pasos:
        1. Clic en el NOMBRE de un producto en el inventario
        Datos: -
        Resultado esperado: Página de detalle del producto (nombre/descripción únicos)
        """)
    @Test
    @DisplayName("TC022.1 - Navegar al detalle de un producto por nombre")
    void TC022_1_navegarDetallePorNombre() {
        String itemName = inventoryPage.navigateToItemFromImage(0);

        boolean tituloCorrecto = productDetailPage.checkItemTitle(itemName);

        assertTrue(driver.getCurrentUrl().contains("item"), "No se redirigió al detalle del item");
        assertTrue(tituloCorrecto, "El título del detalle del item no es el correcto");
    }

    @Story("Navegar a la pantalla de detalle de un producto (clic en imagen)")
    @Description("""
        TC022.2 - Navegar al detalle de un producto haciendo clic en la imagen
        Precondición: Usuario autenticado
        Pasos:
        1. Clic en la IMAGEN de un producto en el inventario
        Datos: -
        Resultado esperado: Página de detalle del producto (nombre/descripción únicos)
        """)
    @Test
    @DisplayName("TC022.2 - Navegar al detalle de un producto por imagen")
    void TC022_2_navegarDetallePorImagen() {
        String itemName = inventoryPage.navigateToItemFromTittle(0);

        boolean tituloCorrecto = productDetailPage.checkItemTitle(itemName);

        assertTrue(driver.getCurrentUrl().contains("item"), "No se redirigió al detalle del item");
        assertTrue(tituloCorrecto, "El título del detalle del item no es el correcto");
    }

    @Story("Volver al inventario desde la pantalla de detalle")
    @Description("""
        TC023 - Volver al inventario desde la pantalla de detalle
        Precondición: Usuario en detalle de producto
        Pasos:
        1. Clic en botón "Back to products"
        Resultado esperado: Usuario vuelve a inventario
        """)
    @Test
    @DisplayName("TC023 - Volver al inventario desde la pantalla de detalle")
    void TC023_volverAInventarioDesdeDetalle() {

        inventoryPage.navigateToItemFromImage(0);
        productDetailPage.clickBackToProducts();
        assertTrue(inventoryPage.isTitleDisplayed(), "El usuario no vuelve correctamente a la página de inventario");
    }

    @Story("Navegación / Checkout")
    @Description("""
    TC030 - Cancelar desde pantalla de información de checkout
    Precondición: Producto en el carrito
    Pasos:
    1. Iniciar checkout
    2. Clic en botón 'Cancel'
    Resultado esperado: Redirección a la pantalla del carrito
    """)
    @Test
    @DisplayName("TC030 - Cancelar desde pantalla de información")
    void TC030_cancelarDesdePantallaInfo() {

        inventoryPage.navigateToCart();
        cartPage.startCheckoutProcess();
        checkoutInformationPage.clickCancelButton();

        assertTrue(driver.getCurrentUrl().contains("cart"), "No se redirigió al carrito");
        assertTrue(cartPage.isTitleDisplayed(), "El título del carrito no es visible");
    }

    @Story("Navegación / Checkout")
    @Description("""
    TC031 - Cancelar desde pantalla de overview de checkout
    Precondición: Producto en el carrito
    Pasos:
    1. Iniciar checkout
    2. Completar datos
    3. Continuar a overview
    4. Clic en botón 'Cancel'
    Resultado esperado: Redirección a la pantalla de inventario (Home)
    """)
    @Test
    @DisplayName("TC031 - Cancelar desde pantalla de overview")
    void TC031_cancelarDesdePantallaOverview() {

        inventoryPage.navigateToCart();
        cartPage.startCheckoutProcess();

        checkoutInformationPage.fillCheckoutForm(name,lastName,postCode);

        checkoutInformationPage.clickContinueButton();

        checkoutOverviewPage.clickCancelButton();

        assertTrue(driver.getCurrentUrl().contains("inventory"), "No se redirigió a la pantalla de productos");
        assertTrue(inventoryPage.isTitleDisplayed(), "No se muestra la pantalla de productos correctamente");
    }

}
