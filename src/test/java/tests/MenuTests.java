package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import pages.AboutPage;
import pages.MenuPage;
import pages.InventoryPage;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Menú Lateral")
@Feature("Navegación menú contextual")
public class MenuTests extends BaseTest {

    private MenuPage menuPage;
    private InventoryPage inventoryPage;
    private LoginPage loginPage;
    private AboutPage aboutPage;

    @BeforeEach
    void initPageObjects() {
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        menuPage = new MenuPage(driver);
        aboutPage = new AboutPage(driver);

        loginPage.loginAs("standard_user", "secret_sauce");
    }

    @Story("Acceder al menú lateral")
    @Description("""
        TC024 - Acceder al menú lateral (hamburguesa)
        Precondición: Usuario autenticado y en la página de inventario
        Pasos:
        1. Clic en icono de menú (hamburguesa)
        Resultado esperado: Menú lateral se muestra
        """)
    @Test
    @DisplayName("TC024 - Acceder al menú lateral (hamburguesa)")
    void TC024_accederMenuLateral() {
        menuPage.openMenu();
        assertTrue(menuPage.isMenuVisible(), "El menú lateral no se muestra correctamente");
    }

    @Story("Cerrar el menú lateral")
    @Description("""
        TC025 - Cerrar el menú lateral (botón X)
        Precondición: Menú lateral abierto
        Pasos:
        1. Clic en botón de cerrar (X)
        Resultado esperado: Menú lateral desaparece
        """)
    @Test
    @DisplayName("TC025 - Cerrar el menú lateral (botón X)")
    void TC025_cerrarMenuLateral() {
        menuPage.openMenu();
        menuPage.closeMenu();

        assertTrue(menuPage.isMenuInvisible(), "El menú lateral no desaparece correctamente");
    }

    @Story("Navegar a All Items")
    @Description("""
        TC026 - Navegar a 'All Items' desde el menú lateral
        Precondición: Menú lateral abierto
        Pasos:
        1. Clic en opción 'All Items'
        Resultado esperado: Redirección a la página de inventario
        """)
    @Test
    @DisplayName("TC026 - Navegar a 'All Items' desde el menú lateral")
    void TC026_navegarAllItems() {
        inventoryPage.navigateToCart();

        menuPage.openMenu();
        menuPage.clickAllItems();

        assertTrue(driver.getCurrentUrl().contains("inventory"), "No se redirige correctamente a 'inventory'");
        assertTrue(inventoryPage.isTitleDisplayed(), "No se redirige correctamente a la página de inventario");
    }

    @Story("Navegar a About")
    @Description("""
        TC027 - Navegar a 'About' desde el menú lateral
        Precondición: Menú lateral abierto
        Pasos:
        1. Clic en opción 'About'
        Resultado esperado: Redirección a https://saucelabs.com/
        """)
    @Test
    @DisplayName("TC027 - Navegar a 'About' desde el menú lateral")
    void TC027_navegarAbout() {
        menuPage.openMenu();
        menuPage.clickAbout();

        assertTrue(aboutPage.isTittleDisplayed(), "No se mustra el titulo de la pantalla de 'About'");
        assertTrue(driver.getCurrentUrl().startsWith("https://saucelabs.com"), "No se redirige correctamente a 'About'");
    }

    @Story("Logout desde el menú lateral")
    @Description("""
        TC028 - Logout desde el menú lateral
        Precondición: Menú lateral abierto
        Pasos:
        1. Clic en opción 'Logout'
        Resultado esperado: Redirección a la pantalla de login
        """)
    @Test
    @DisplayName("TC028 - Logout desde el menú lateral")
    void TC028_logoutMenuLateral() {
        menuPage.openMenu();
        menuPage.clickLogout();

        assertTrue(loginPage.isTittleDisplayed(), "No se redirige correctamente a la pantalla de login");
        assertTrue(driver.getCurrentUrl().startsWith("https://www.saucedemo.com"), "No se redirige correctamente a la pantalla de login");
    }

    @Story("Reset App State desde el menú lateral")
    @Description("""
        TC029 - Reset App State desde el menú lateral
        Precondición: Menú lateral abierto
        Pasos:
        1. Clic en opción 'Reset App State'
        Resultado esperado: Aplicación vuelve a estado inicial (carrito vacío, filtros reset)
        """)
    @Test
    @DisplayName("TC029 - Reset App State desde el menú lateral")
    void TC029_resetAppState() {

        inventoryPage.addItemsToCart(3);

        inventoryPage.checkCartIconBadge(3);

        menuPage.openMenu();

        menuPage.clickResetAppState();

        assertFalse(inventoryPage.isCartIconBadgeVisible(), "La aplicación no vuelve a su estado inicial correctamente");
    }
}