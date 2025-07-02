package suites;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import tests.*;

@Suite
@SelectClasses({
        LoginTests.class,
        InventoryTests.class,
        CartTests.class,
        CheckOutTests.class,
        NavigationTests.class,
        MenuTests.class,
})
public class SmokeTestSuite {

}

