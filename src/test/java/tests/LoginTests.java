package tests;

import org.junit.jupiter.api.*;
import pages.LoginPage;
import pages.InventoryPage;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTests extends BaseTest {

    private LoginPage loginPage;

    @BeforeEach
    void initPageObjects() {
        loginPage = new LoginPage(driver);
    }

    @Test
    @DisplayName("TC001 - Login exitoso con credenciales válidas")
    void TC001_loginCorrecto() {
        loginPage.loginAs("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);

        assertTrue(driver.getCurrentUrl().contains("inventory"), "El login no redirige correctamente");
        assertTrue(inventoryPage.isTitleVisible(), "El título 'Products' no está visible o es incorrecto");
    }
}
