package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import pages.LoginPage;
import pages.InventoryPage;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Login")
@Feature("Autenticación")
public class LoginTests extends BaseTest {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    @BeforeEach
    void initPageObjects() {
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
    }

    @Story("Login exitoso")
    @Description("""
        TC001 - Login exitoso con usuario válido
        Precondición: Navegador en https://saucedemo.com/
        Pasos:
        1. Ingresar usuario
        2. Ingresar password
        3. Clic en Login
        Datos: standard_user / secret_sauce
        Resultado esperado: Redirección a página de inventario
        """)
    @Test
    @DisplayName("TC001 - Login exitoso con credenciales válidas")
    void TC001_loginCorrecto() {
        loginPage.loginAs("standard_user", "secret_sauce");
        assertTrue(driver.getCurrentUrl().contains("inventory"), "El login no redirige correctamente");
        assertTrue(inventoryPage.isTitleVisible(), "El título 'Products' no está visible o es incorrecto");
    }

    @Story("Login inválido")
    @Description("""
        TC002 - Login con credenciales inválidas
        Precondición: Navegador en https://saucedemo.com/
        Pasos:
        1. Ingresar usuario incorrecto
        2. Ingresar password incorrecta
        3. Clic en Login
        Datos: usuario_x / secret_sauce
        Resultado esperado: Mostrar mensaje de error de autenticación
        """)
    @Test
    @DisplayName("TC002 - Login con credenciales inválidas")
    void TC002_loginIncorrecto() {
        loginPage.loginAs("usuario_x", "secret_sauce");
        assertTrue(loginPage.isInvalidUserErrorVisible(), "El mensaje de error no es correcto");
    }

    @Story("Login usuario bloqueado")
    @Description("""
        TC003 - Login con usuario bloqueado
        Precondición: Navegador en https://saucedemo.com/
        Pasos:
        1. Ingresar usuario bloqueado
        2. Ingresar password
        3. Clic en Login
        Datos: locked_out_user / secret_sauce
        Resultado esperado: Mostrar mensaje: usuario bloqueado
        """)
    @Test
    @DisplayName("TC003 - Login con usuario bloqueado")
    void TC003_loginUsuarioBloqueado() {
        loginPage.loginAs("locked_out_user", "secret_sauce");
        assertTrue(loginPage.isLockedUserErrorVisible(), "El mensaje de error no es correcto");
    }

    @Story("Login usuario vacío")
    @Description("""
        TC004 - Login con usuario vacío
        Precondición: Navegador en https://saucedemo.com/
        Pasos:
        1. Dejar usuario vacío
        2. Ingresar password
        3. Clic en Login
        Datos: "" / secret_sauce
        Resultado esperado: Mostrar mensaje de campo obligatorio
        """)
    @Test
    @DisplayName("TC004 - Login con usuario vacío")
    void TC004_loginUsuarioVacio() {
        loginPage.loginAs("", "secret_sauce");
        assertTrue(loginPage.isNoUserErrorVisible(), "El mensaje de error no es correcto");
    }

    @Story("Login password vacía")
    @Description("""
        TC005 - Login con contraseña vacía
        Precondición: Navegador en https://saucedemo.com/
        Pasos:
        1. Introducir usuario
        2. Dejar contraseña vacía
        3. Clic en Login
        Datos: standard_user / ""
        Resultado esperado: Mostrar mensaje de campo obligatorio
        """)
    @Test
    @DisplayName("TC005 - Login con contraseña vacía")
    void TC005_loginContrasenyaVacia() {
        loginPage.loginAs("standard_user", "");
        assertTrue(loginPage.isNoPsswdErrorVisible(), "El mensaje de error no es correcto");
    }
}