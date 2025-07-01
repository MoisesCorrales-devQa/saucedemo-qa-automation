package tests;

import model.ProductItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.InventoryPage;
import pages.LoginPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTests extends BaseTest {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    //Strings

    private final String AZ = "az";
    private final String ZA = "za";
    private final String ASCENDENTE = "lohi";
    private final String DESCENDENTE = "hilo";

    @BeforeEach
    void initPageObjects() {
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
    }

    @BeforeEach
    void login(){
        //PRECONDICION: Login correcto
        loginPage.loginAs("standard_user", "secret_sauce");
    }

    @Test
    @DisplayName("TC006 - Validar nombre, descripción y precio")
    void TC006_visibilidadCamposItems() {

        //STEP 1. Acceder al inventario

        // STEP 2. Revisar nombre, descripción y precio por ítem

        List<ProductItem> products = inventoryPage.getVisibleProducts();

        //SPECTED_RESULT: Todos los datos deben mostrarse correctamente

        assertFalse(products.isEmpty(), "No hay productos visibles");

        for (ProductItem product : products) {
            assertFalse(product.getName().isEmpty(), "El nombre del producto está vacío");
            assertFalse(product.getDescription().isEmpty(), "La descripción del producto está vacía");
            assertTrue(product.getPrice().matches("\\$\\d+\\.\\d{2}"), "El precio no tiene el formato correcto: " + product.getPrice());
        }
    }

    @Test
    @DisplayName("TC007.1 - Ordenamiento de productos A-Z")
    void TC007_1_ordenamientoProductosA_Z() {

        //STEP 1. Abrir menú de ordenamiento

        // STEP 2. Seleccionar A-Z

        inventoryPage.selectOrder(AZ);

        List<ProductItem> products = inventoryPage.getVisibleProducts();

        //SPECTED_RESULT: Lista ordenada alfabéticamente ascendente

        assertFalse(products.isEmpty(), "No hay productos visibles");

        List<String> nombresVisibles = products.stream()
                .map(ProductItem::getName)
                .toList();

        List<String> nombresOrdenados = new ArrayList<>(nombresVisibles);
        Collections.sort(nombresOrdenados); // Orden alfabético A-Z

        assertEquals(nombresOrdenados, nombresVisibles, "Los productos no están ordenados alfabéticamente (A-Z)");
    }

    @Test
    @DisplayName("TC007.2 - Ordenamiento de productos Z-A")
    void TC007_2_ordenamientoProductosZ_A() {

        //STEP 1. Abrir menú de ordenamiento

        // STEP 2. Seleccionar Z-A

        inventoryPage.selectOrder(ZA);

        List<ProductItem> products = inventoryPage.getVisibleProducts();

        //SPECTED_RESULT: Lista ordenada alfabéticamente descendente

        assertFalse(products.isEmpty(), "No hay productos visibles");

        List<String> nombresVisibles = products.stream()
                .map(ProductItem::getName)
                .toList();

        List<String> nombresOrdenados = new ArrayList<>(nombresVisibles);
        Collections.sort(nombresOrdenados);
        Collections.reverse(nombresOrdenados); // Orden alfabético Z-A

        assertEquals(nombresOrdenados, nombresVisibles, "Los productos no están ordenados alfabéticamente (Z-A)");
    }

    @Test
    @DisplayName("TC008.1 - Ordenamiento de productos precio ascendente")
    void TC008_1_ordenamientoProductosPrecioAscendente() {

        //STEP 1. Abrir menú de ordenamiento

        // STEP 2. Seleccionar precio ascendente

        inventoryPage.selectOrder(ASCENDENTE);

        List<ProductItem> products = inventoryPage.getVisibleProducts();

        //SPECTED_RESULT: Lista ordenada por precio ascendente

        assertFalse(products.isEmpty(), "No hay productos visibles");

        List<Double> preciosVisibles = new ArrayList<>();
        for (ProductItem product : products) {
            assertFalse(product.getName().isEmpty(), "El nombre del producto está vacío");
            assertFalse(product.getDescription().isEmpty(), "La descripción del producto está vacía");

            String priceText = product.getPrice();
            assertTrue(priceText.matches("\\$\\d+\\.\\d{2}"), "El precio no tiene el formato correcto: " + priceText);

            double precio = Double.parseDouble(priceText.replace("$", ""));
            preciosVisibles.add(precio);
        }

        List<Double> preciosOrdenados = new ArrayList<>(preciosVisibles);
        Collections.sort(preciosOrdenados); // Orden por precio ascendente

        assertEquals(preciosOrdenados, preciosVisibles, "Los productos no están ordenados por precio ascendente");
    }

    @Test
    @DisplayName("TC008.2 - Ordenamiento de productos precio descendente")
    void TC008_2_ordenamientoProductosPrecioDescendente() {

        //STEP 1. Abrir menú de ordenamiento

        // STEP 2. Seleccionar seleccionar precio descendente

        inventoryPage.selectOrder(DESCENDENTE);

        List<ProductItem> products = inventoryPage.getVisibleProducts();

        //SPECTED_RESULT: Lista ordenada por precio descendente

        assertFalse(products.isEmpty(), "No hay productos visibles");

        List<Double> preciosVisibles = new ArrayList<>();
        for (ProductItem product : products) {
            assertFalse(product.getName().isEmpty(), "El nombre del producto está vacío");
            assertFalse(product.getDescription().isEmpty(), "La descripción del producto está vacía");

            String priceText = product.getPrice();
            assertTrue(priceText.matches("\\$\\d+\\.\\d{2}"), "El precio no tiene el formato correcto: " + priceText);

            // Extraer número y guardar
            double precio = Double.parseDouble(priceText.replace("$", ""));
            preciosVisibles.add(precio);
        }

        List<Double> preciosOrdenados = new ArrayList<>(preciosVisibles);
        Collections.sort(preciosOrdenados);
        Collections.reverse(preciosOrdenados); // Orden precio desscendente

        assertEquals(preciosOrdenados, preciosVisibles, "Los productos no están ordenados por precio descendente");
    }
}
