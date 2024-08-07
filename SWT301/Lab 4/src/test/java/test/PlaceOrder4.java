package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PlaceOrder4 {
    @Before
    public void setup() throws InterruptedException {
        AIO.setup();
        AIO.login();
    }

    @Test
    public void testCartExceedsStock() throws InterruptedException {
        AIO.addItem("category=cpu&id=18");
        AIO.increase(10);
        AIO.assertEquals("TestProductError", "Not enough stock");
    }

    @After
    public void clean() throws InterruptedException {
        AIO.clearCart();
        AIO.quit();
    }
}