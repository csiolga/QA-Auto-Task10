package shop;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RealItemTestWithTestNG {
    String name;
    double price;
    double weight;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        name = "BMW";
        price = 30500.75;
        weight = 1490.5;
    }

    @AfterMethod
    public void tearDown() throws Exception {
    }

    @Test(groups = { "positive" })
    void verifyRealItemParameters() {
        RealItem testRealItem = new RealItem();
        testRealItem.setName(name);
        testRealItem.setPrice(price);
        testRealItem.setWeight(weight);

        String result = "Class: class shop.RealItem; Name: " + name + "; Price: " + price + "; Weight: "+ weight;

        Assert.assertEquals(result, testRealItem.toString());
    }
}