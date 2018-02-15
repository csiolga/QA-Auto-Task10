package shop;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class VirtualItemTestWithJUnit {
    String name;
    double price;
    double size;
    VirtualItem testVirtualItem;

    @BeforeEach
    void setUp() {
        name = "Movie";
        price = 10.5;
        size = 1250.97;
        testVirtualItem = new VirtualItem();
    }

    @AfterEach
    void tearDown() {}

    @Test
    @Tag("positive")
    void verifyVirtualItemParameters() {
        testVirtualItem.setName(name);
        testVirtualItem.setPrice(price);
        testVirtualItem.setSizeOnDisk(size);

        assertAll("test virtual item attributes",
                () -> assertEquals(name, testVirtualItem.getName()),
                () -> assertEquals(price, testVirtualItem.getPrice()),
                () -> assertEquals(size, testVirtualItem.getSizeOnDisk())
        );
    }
}