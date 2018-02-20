package shop;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CartTestWithTestNG {
    Cart testCart;
    RealItem testRealItem;
    VirtualItem testVirtualItem;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
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

    @AfterMethod
    public void tearDown() throws Exception {
    }

    @Test(groups = { "positive" })
    void verifyCartTotalPrice(){
        testCart.addRealItem(testRealItem);
        testCart.addVirtualItem(testVirtualItem);

        double itemsTotalPrice = testRealItem.getPrice() + testVirtualItem.getPrice();
        double cartTotalPrice = itemsTotalPrice + itemsTotalPrice * 0.2;

        double result = testCart.getTotalPrice();

        assertEquals(cartTotalPrice, result);
    }
}