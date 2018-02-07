package parser;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.testng.Assert;
import shop.Cart;
import parser.JsonParser;
import parser.Parser;
import shop.RealItem;
import shop.VirtualItem;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class JsonParserTest {
    Parser parser;
    Cart testCart;

    @BeforeEach
    void setUp() {
      parser = new JsonParser();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Tag("positive")
    void writeToFile_newFileCreated_test() {
        testCart = new Cart("test-cart");
        parser.writeToFile(testCart);

        Path filePath = Paths.get("src/main/resources/" + testCart.getCartName() + ".json");

        assertTrue(Files.exists(filePath));
    }

    @Test
    @Tag("positive")
    void readFomFile_newCartContainsCorrectCartName_test() {
        testCart = new Cart("test-cart1");
        parser.writeToFile(testCart);

        Cart testCart2 = parser.readFromFile(new File("src/main/resources/" + testCart.getCartName() +  ".json"));

        assertEquals(testCart.getCartName(), testCart2.getCartName());
    }

    @Disabled
    @Test
    @Tag("exception")
    void writeToFile_throwsFileNotFoundExceptionOnIncorrectFileName_Test() {
        Throwable exception = assertThrows(FileNotFoundException.class,
                ()->{
                    testCart = new Cart("?/|:*");
                    parser.writeToFile(testCart);
                });
    }

    @ParameterizedTest
    @Tag("exception")
    @ValueSource(strings = { "src/main/resources/no-file.json", "", "src/main/resources",
            "src/main/resources/?/|:*.json", "src/main/resources/newFolder/test-cart.json" })
    void readFromFile_throwsNoSuchFileExceptionOnIncorrectFilePath_Test(String filePath) {
        Throwable exception = assertThrows(NoSuchFileException.class,
                ()->{
                    testCart = parser.readFromFile(new File(filePath));
                });
    }
}