package suites;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import tests.CartTests;
import tests.CheckOutTests;
import tests.InventoryTests;
import tests.LoginTests;

@Suite
@SelectClasses({
        LoginTests.class,
        InventoryTests.class,
        CartTests.class,
        CheckOutTests.class,
})
public class SmokeTestSuite {
    // No necesita cuerpo, la anotación hace todo
}

