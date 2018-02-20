package shop;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VirtualItemTestWithTestNG {
    VirtualItem testVirtualItem;
    private SoftAssert softAssert = new SoftAssert();

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        testVirtualItem = new VirtualItem();
    }

    @AfterMethod
    public void tearDown() throws Exception {
    }

    @Parameters({ "name", "price", "size" })
    @Test(groups = { "positive" })
    void verifyVirtualItemParameters(String name, double price, double size) {
        testVirtualItem.setName(name);
        testVirtualItem.setPrice(price);
        testVirtualItem.setSizeOnDisk(size);

        softAssert.assertEquals(name, testVirtualItem.getName(), "Virtual item name");
        softAssert.assertEquals(price, testVirtualItem.getPrice(), "Virtual item price");
        softAssert.assertEquals(size, testVirtualItem.getSizeOnDisk(), "Virtual item size");
        softAssert.assertAll();
    }
}