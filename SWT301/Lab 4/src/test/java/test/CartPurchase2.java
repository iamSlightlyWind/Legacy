package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CartPurchase2 {
    @Before
    public void setup() throws InterruptedException {
        AIO.setup();
        AIO.login();
    }

    @Test
    public void testCheckCart() throws InterruptedException {
        AIO.addItem("category=cpu&id=18");
        AIO.addItem("category=gpu&id=43");
        AIO.addItem("category=mobo&id=107");
        AIO.checkout();
        AIO.openNewTab("/Cart");
        Thread.sleep(1500);
        AIO.assertEquals("TestCartEmpty", "No products found");
    }

    @After
    public void clean() throws InterruptedException {
        AIO.clearCart();
        AIO.quit();
    }
}