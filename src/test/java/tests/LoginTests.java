package tests;

import org.junit.jupiter.api.*;
import pages.LoginPage;
import pages.InventoryPage;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTests extends BaseTest {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    @BeforeEach
    void initPageObjects() {
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
    }

    //@Test
    @DisplayName("TC001 - Login exitoso con credenciales válidas")
    void TC001_loginCorrecto() {

        //STEPS
        //1. Ingresar usuario | 2. Ingresar password | 3. Clic en Login


        //SPECTED_CONDITION: Redirección a página de inventario

        loginPage.loginAs("standard_user", "secret_sauce");

        assertTrue(driver.getCurrentUrl().contains("inventory"), "El login no redirige correctamente");
        assertTrue(inventoryPage.isTitleVisible(), "El título 'Products' no está visible o es incorrecto");
    }

    @Test
    @DisplayName("TC002 - Login con credenciales inválidas")
    void TC002_loginIncorrecto() {

        //STEPS
        //1. Ingresar usuario incorrecto | 2. Ingresar password incorrecta | 3. Clic en Login
        loginPage.loginAs("error_user", "secret_error");

        //SPECTED_CONDITION: Mostrar mensaje de error de autenticación
        assertTrue(loginPage.isInvalidUserErrorVisible(), "El mensaje de error no es correcto");
    }

    @Test
    @DisplayName("TC003 - Login con usuario bloqueado")
    void TC003_loginIncorrecto() {

        //STEPS
        //1. Ingresar usuario incorrecto bloqueado | 2. Ingresar password incorrecta | 3. Clic en Login
        loginPage.loginAs("locked_out_user", "secret_sauce");

        //SPECTED_CONDITION: Mostrar mensaje: usuario bloqueado
        assertTrue(loginPage.isLockedUserErrorVisible(), "El mensaje de error no es correcto");
    }
}
