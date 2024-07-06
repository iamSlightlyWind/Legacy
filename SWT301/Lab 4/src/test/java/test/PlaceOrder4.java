package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PlaceOrder4 {
    @Before
    public void setup() throws InterruptedException{
        AIO.login();
    }

    @Test
    public void testCartExceedsStock() throws InterruptedException{
        AIO.addOneItem("category=cpu&id=18");
        AIO.increase(10);
    }
    
    @After
    public void clean() throws InterruptedException{
        AIO.clearCart();
    }
}