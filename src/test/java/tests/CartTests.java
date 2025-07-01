package tests;

import model.CartItem;
import model.ProductItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CartTests extends BaseTest {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;

    @BeforeEach
    void setUp() {
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);

        loginPage.loginAs("standard_user", "secret_sauce");
    }

    @Test
    @DisplayName("TC009 - Añadir productos al carrito")
    void TC009_cantidadIconoCarrito() {

        //STEPS
        //1. Acceder al inventario | 2. Clic en "Add to cart" en uno o más productos

        final int amount = 3;

        inventoryPage.addItemsToCart(amount);

        //SPECTED_RESULT: El ícono del carrito refleja la cantidad

        boolean validateAmount = inventoryPage.checkCartIconBadge(amount);

        assertTrue(validateAmount, "El numero del carrito no se correspodnde con la cantidad añadida");
    }

    @Test
    @DisplayName("TC010 - Verificar productos en el carrito")
    void TC010_VerificarProdctosCarrito() {

        //PRECONDICION: Items añadidos al carrito

        List<ProductItem> addedItems = inventoryPage.addItemsToCart(3);

        //STEP 1. Clic en icono del carrito

        inventoryPage.navigateToCart();

        //STEP 2. Verificar nombre y cantidad del producto

        List<CartItem> products = cartPage.getCartItems();

        //SPECTED_RESULT: Todos los datos deben mostrarse correctamente

        assertFalse(addedItems.isEmpty(), "No hay productos visibles");

        verificarItemsEnCarrito(addedItems, products);
    }

    @Test
    @DisplayName("TC011 - Eliminar productos del carrito")
    void TC011_VerificarProdctosEliminados() {

        final int eliminado = 1;

        //PRECONDICION: Items añadidos al carrito

        List<ProductItem> addedItems = inventoryPage.addItemsToCart(3);

        //STEP 1. Clic en icono del carrito

        inventoryPage.navigateToCart();

        //STEP 2. Clic en “Remove” en un producto

        cartPage.removeItemFromCart(eliminado);

        List<CartItem> products = cartPage.getCartItems();

        addedItems.remove(eliminado);

        //SPECTED_RESULT: Producto eliminado del listado

        assertFalse(addedItems.isEmpty(), "No hay productos visibles");

        verificarItemsEnCarrito(addedItems, products);
    }


    private void verificarItemsEnCarrito(List<ProductItem> expectedItems, List<CartItem> actualItems) {
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
