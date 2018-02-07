package shop;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {
    Cart testCart;
    RealItem testRealItem;
    VirtualItem testVirtualItem;

    @BeforeEach
    void setUp() {
        testCart = new Cart("test-cart");
        testRealItem = new RealItem();
        testRealItem.setName("BMW");
        testRealItem.setPrice(10500);
        testRealItem.setWeight(1490.75);

        testVirtualItem = new VirtualItem();
        testVirtualItem.setName("Movie");
        testVirtualItem.setPrice(9.99);
        testVirtualItem.setSizeOnDisk(1540);
    }

    @AfterEach
    void tearDown() {}

    @Test
    @Tag("positive")
    void getCartTotalPrice_Test(){
        testCart.addRealItem(testRealItem);
        testCart.addVirtualItem(testVirtualItem);

        double itemsTotalPrice = testRealItem.getPrice() + testVirtualItem.getPrice();
        double cartTotalPrice = itemsTotalPrice + itemsTotalPrice * 0.2;

        double result = testCart.getTotalPrice();

        assertEquals(cartTotalPrice, result);
    }
}