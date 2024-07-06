package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PlaceOrder2 {
    @Before
    public void setup() throws InterruptedException{
        AIO.setup();
        AIO.login();
    }

    @Test
    public void testCartEmpty() throws InterruptedException{
        AIO.addItem("category=cpu&id=18");
        AIO.clearCart();
        AIO.assertNotEquals("TestCartError", "Cart is empty!");
    }
    
    @After
    public void clean() throws InterruptedException{
        AIO.clearCart();
        AIO.quit();
    }
}