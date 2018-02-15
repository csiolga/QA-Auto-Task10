package shop;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RealItemTestWithJUnit {
    String name;
    double price;
    double weight;

    @BeforeEach
    void setUp() {
        name = "BMW";
        price = 30500.75;
        weight = 1490.5;
    }

    @AfterEach
    void tearDown() {}

    @Test
    @Tag("positive")
    void verifyRealItemParameters() {
        RealItem testRealItem = new RealItem();
        testRealItem.setName(name);
        testRealItem.setPrice(price);
        testRealItem.setWeight(weight);

        String result = "Class: class shop.RealItem; Name: " + name + "; Price: " + price + "; Weight: "+ weight;

        assertEquals(result, testRealItem.toString());
    }
}