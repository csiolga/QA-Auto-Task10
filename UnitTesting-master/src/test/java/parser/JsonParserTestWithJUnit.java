package parser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JsonParserTestWithJUnit {
    Parser parser;
    Cart testCart;

    @BeforeEach
    void setUp() {
        parser = new JsonParser();
    }

    @AfterEach
    void tearDown() {}

    @Test
    @Tag("positive")
    void verifyNewFileCreated() {
        testCart = new Cart("test-cart");
        parser.writeToFile(testCart);

        Path filePath = Paths.get("src/main/resources/" + testCart.getCartName() + ".json");

        assertTrue(Files.exists(filePath), "src/main/resources/" + testCart.getCartName() + ".json file should be created");
    }

    @Test
    @Tag("positive")
    void verifyCartNameFromFile() {
        testCart = new Cart("test-cart1");
        parser.writeToFile(testCart);

        Cart newCartFromFile = parser.readFromFile(new File("src/main/resources/" + testCart.getCartName() +  ".json"));

        assertEquals(testCart.getCartName(), newCartFromFile.getCartName());
    }

    @Disabled
    @Test
    @Tag("exception")
    void verifyFileNotFoundExceptionThrownOnWritingToFile() {
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
    void verifyNoSuchFileExceptionThrownOnReadingFromFile(String filePath) {
        Throwable exception = assertThrows(NoSuchFileException.class,
                ()->{
                    testCart = parser.readFromFile(new File(filePath));
                });
    }
}