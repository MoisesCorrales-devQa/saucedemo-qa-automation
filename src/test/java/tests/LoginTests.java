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

    @Test
    @DisplayName("TC001 - Login exitoso con credenciales válidas")
    void TC001_loginCorrecto() {

        //STEPS
        //1. Ingresar usuario | 2. Ingresar password | 3. Clic en Login

        loginPage.loginAs("standard_user", "secret_sauce");

        //SPECTED_RESULT: Redirección a página de inventario

        assertTrue(driver.getCurrentUrl().contains("inventory"), "El login no redirige correctamente");
        assertTrue(inventoryPage.isTitleVisible(), "El título 'Products' no está visible o es incorrecto");
    }

    @Test
    @DisplayName("TC002 - Login con credenciales inválidas")
    void TC002_loginIncorrecto() {

        //STEPS
        //1. Ingresar usuario incorrecto | 2. Ingresar password incorrecta | 3. Clic en Login
        loginPage.loginAs("usuario_x", "secret_sauce");

        //SPECTED_RESULT: Mostrar mensaje de error de autenticación
        assertTrue(loginPage.isInvalidUserErrorVisible(), "El mensaje de error no es correcto");
    }

    @Test
    @DisplayName("TC003 - Login con usuario bloqueado")
    void TC003_loginIncorrecto() {

        //STEPS
        //1. Ingresar usuario incorrecto bloqueado | 2. Ingresar password incorrecta | 3. Clic en Login
        loginPage.loginAs("locked_out_user", "secret_sauce");

        //SPECTED_RESULT: Mostrar mensaje: usuario bloqueado
        assertTrue(loginPage.isLockedUserErrorVisible(), "El mensaje de error no es correcto");
    }

    @Test
    @DisplayName("TC004 - Login con usuario vacío")
    void TC004_loginUsuarioVacio() {

        //STEPS
        //1. Dejar usuario vacío | 2. Ingresar password | 3. Clic en Login
        loginPage.loginAs("", "secret_sauce");

        //SPECTED_RESULT: Mostrar mensaje de campo obligatorio
        assertTrue(loginPage.isNoUserErrorVisible(), "El mensaje de error no es correcto");
    }

    @Test
    @DisplayName("TC004 - Login con contraseña vacía")
    void TC005_loginContrasenyaVacia() {

        //STEPS
        //1. Introducir usuario | 2. Dejar contraseña vacía | 3. Clic en Login
        loginPage.loginAs("secret_sauce", "");

        //SPECTED_RESULT: Mostrar mensaje: usuario bloqueado
        assertTrue(loginPage.isNoPsswdErrorVisible(), "El mensaje de error no es correcto");
    }
}
