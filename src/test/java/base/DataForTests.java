package base;

import org.testng.annotations.DataProvider;

public class DataForTests {

    @DataProvider(name = "dataForValidLogin")
    public static Object[][] dataForValidLogin() {

        return new Object[][]{
                {"ba-bengal", "123"}
        };

    }

    @DataProvider(name = "dataForInValidLogin")
    public static Object[][] dataForInValidLogin() {

        return new Object[][]{
                {"123", "ba-bengal"}
        };

    }
}
