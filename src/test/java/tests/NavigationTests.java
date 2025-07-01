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
import pages.ItemDetailPage;
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
    private CheckoutInformationPage checkoutInformationPage;
    private CheckoutOverviewPage checkoutOverviewPage;
    private CheckOutCompletePage checkoutCompletePage;
    private ItemDetailPage itemDetailPage;

    @BeforeEach
    void setUp() {
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checkoutInformationPage = new CheckoutInformationPage(driver);
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        checkoutCompletePage = new CheckOutCompletePage(driver);
        itemDetailPage = new ItemDetailPage(driver);

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
        assertTrue(cartPage.isTitleVisible(), "El título del carrito no es visible");
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
    //@Test
    @DisplayName("TC021 - Volver al inventario desde el carrito")
    void TC021_volverInventarioDesdeCarrito() {

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

        boolean tituloCorrecto = itemDetailPage.checkItemTitle(itemName);

        assertTrue(driver.getCurrentUrl().contains("item"), "No se redirigió al detalle del item");
        assertTrue(tituloCorrecto, "El título del carrito no es visible");
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

        boolean tituloCorrecto = itemDetailPage.checkItemTitle(itemName);

        assertTrue(driver.getCurrentUrl().contains("item"), "No se redirigió al detalle del item");
        assertTrue(tituloCorrecto, "El título del carrito no es visible");
    }

}
