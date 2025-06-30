package suites;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import tests.InventoryTests;
import tests.LoginTests;

@Suite
@SelectClasses({
        LoginTests.class,
        InventoryTests.class
})
public class SmokeTestSuite {
    // No necesita cuerpo, la anotación hace todo
}

