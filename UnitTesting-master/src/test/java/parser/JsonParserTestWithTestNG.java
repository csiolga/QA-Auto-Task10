package parser;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import shop.Cart;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class JsonParserTestWithTestNG {
    Parser parser;
    Cart testCart;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        parser = new JsonParser();
    }

    @AfterMethod
    public void tearDown() throws Exception {
    }

    @Test(groups = { "positive" })
    public void verifyNewFileCreated() {
        testCart = new Cart("test-cart");
        parser.writeToFile(testCart);

        Path filePath = Paths.get("src/main/resources/" + testCart.getCartName() + ".json");

        assertTrue(Files.exists(filePath), "src/main/resources/" + testCart.getCartName() + ".json file should be created");
    }

    @Test(groups = { "positive" })
    public void verifyCartNameFromFile() {
        testCart = new Cart("test-cart1");
        parser.writeToFile(testCart);

        Cart newCartFromFile = parser.readFromFile(new File("src/main/resources/" + testCart.getCartName() +  ".json"));

        assertEquals(testCart.getCartName(), newCartFromFile.getCartName());
    }

    @Test(enabled = false, groups = { "exception" }, expectedExceptions = FileNotFoundException.class)
    public void verifyFileNotFoundExceptionThrownOnWritingToFile() {
                    testCart = new Cart("?/|:*");
                    parser.writeToFile(testCart);
    }

    @DataProvider(name = "filePath")
    public Object[] filePaths() {
        return new Object[] { "src/main/resources/no-file.json", "", "src/main/resources",
                "src/main/resources/?/|:*.json", "src/main/resources/newFolder/test-cart.json" };
    }

    @Test(groups = { "exception" }, dataProvider = "filePath", expectedExceptions = NoSuchFileException.class)
    public void verifyNoSuchFileExceptionThrownOnReadingFromFile(String filePath) {
                    testCart = parser.readFromFile(new File(filePath));
    }
}