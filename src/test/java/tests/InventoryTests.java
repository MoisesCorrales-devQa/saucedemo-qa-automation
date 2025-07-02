package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
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

@Epic("Inventario")
@Feature("Visualización y Ordenamiento")
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

    @Story("Validar nombre, descripción y precio")
    @Description("""
        TC006 - Validar nombre, descripción y precio
        Precondición: Usuario autenticado
        Pasos:
        1. Acceder al inventario
        2. Revisar nombre, descripción y precio por ítem
        Datos: -
        Resultado esperado: Todos los datos deben mostrarse correctamente
        """)
    @Test
    @DisplayName("TC006 - Validar nombre, descripción y precio")
    void TC006_visibilidadCamposItems() {

        List<ProductItem> products = inventoryPage.getVisibleProducts();

        assertFalse(products.isEmpty(), "No hay productos visibles");

        for (ProductItem product : products) {
            assertFalse(product.getName().isEmpty(), "El nombre del producto está vacío");
            assertFalse(product.getDescription().isEmpty(), "La descripción del producto está vacía");
            assertTrue(product.getPrice().matches("\\$\\d+\\.\\d{2}"), "El precio no tiene el formato correcto: " + product.getPrice());
        }
    }

    @Story("Ordenamiento A-Z")
    @Description("""
        TC007.1 - Ordenamiento de productos A-Z
        Precondición: Usuario autenticado
        Pasos:
        1. Abrir menú de ordenamiento
        2. Seleccionar A-Z
        Datos: -
        Resultado esperado: Lista ordenada alfabéticamente ascendente
        """)
    @Test
    @DisplayName("TC007.1 - Ordenamiento de productos A-Z")
    void TC007_1_ordenamientoProductosA_Z() {

        inventoryPage.selectOrder(AZ);

        List<ProductItem> products = inventoryPage.getVisibleProducts();

        assertFalse(products.isEmpty(), "No hay productos visibles");

        List<String> nombresVisibles = products.stream()
                .map(ProductItem::getName)
                .toList();

        List<String> nombresOrdenados = new ArrayList<>(nombresVisibles);
        Collections.sort(nombresOrdenados); // Orden alfabético A-Z

        assertEquals(nombresOrdenados, nombresVisibles, "Los productos no están ordenados alfabéticamente (A-Z)");
    }

    @Story("Ordenamiento Z-A")
    @Description("""
        TC007.2 - Ordenamiento de productos Z-A
        Precondición: Usuario autenticado
        Pasos:
        1. Abrir menú de ordenamiento
        2. Seleccionar Z-A
        Datos: -
        Resultado esperado: Lista ordenada alfabéticamente descendente
        """)
    @Test
    @DisplayName("TC007.2 - Ordenamiento de productos Z-A")
    void TC007_2_ordenamientoProductosZ_A() {

        inventoryPage.selectOrder(ZA);

        List<ProductItem> products = inventoryPage.getVisibleProducts();

        assertFalse(products.isEmpty(), "No hay productos visibles");

        List<String> nombresVisibles = products.stream()
                .map(ProductItem::getName)
                .toList();

        List<String> nombresOrdenados = new ArrayList<>(nombresVisibles);
        Collections.sort(nombresOrdenados);
        Collections.reverse(nombresOrdenados); // Orden alfabético Z-A

        assertEquals(nombresOrdenados, nombresVisibles, "Los productos no están ordenados alfabéticamente (Z-A)");
    }

    @Story("Ordenamiento precio ascendente")
    @Description("""
        TC008.1 - Ordenamiento de productos precio ascendente
        Precondición: Usuario autenticado
        Pasos:
        1. Abrir menú de ordenamiento
        2. Seleccionar precio ascendente
        Datos: -
        Resultado esperado: Lista ordenada por precio ascendente
        """)
    @Test
    @DisplayName("TC008.1 - Ordenamiento de productos precio ascendente")
    void TC008_1_ordenamientoProductosPrecioAscendente() {

        inventoryPage.selectOrder(ASCENDENTE);

        List<ProductItem> products = inventoryPage.getVisibleProducts();

        assertFalse(products.isEmpty(), "No hay productos visibles");

        List<Double> preciosVisibles = new ArrayList<>();

        List<Double> preciosOrdenados = new ArrayList<>(preciosVisibles);
        Collections.sort(preciosOrdenados);

        assertEquals(preciosOrdenados, preciosVisibles, "Los productos no están ordenados por precio ascendente");
    }

    @Story("Ordenamiento precio descendente")
    @Description("""
        TC008.2 - Ordenamiento de productos precio descendente
        Precondición: Usuario autenticado
        Pasos:
        1. Abrir menú de ordenamiento
        2. Seleccionar precio descendente
        Datos: -
        Resultado esperado: Lista ordenada por precio descendente
        """)
    @Test
    @DisplayName("TC008.2 - Ordenamiento de productos precio descendente")
    void TC008_2_ordenamientoProductosPrecioDescendente() {

        inventoryPage.selectOrder(DESCENDENTE);

        List<ProductItem> products = inventoryPage.getVisibleProducts();

        assertFalse(products.isEmpty(), "No hay productos visibles");

        List<Double> preciosVisibles = new ArrayList<>();

        List<Double> preciosOrdenados = new ArrayList<>(preciosVisibles);
        Collections.sort(preciosOrdenados);
        Collections.reverse(preciosOrdenados); // Orden precio desscendente

        assertEquals(preciosOrdenados, preciosVisibles, "Los productos no están ordenados por precio descendente");
    }
}
