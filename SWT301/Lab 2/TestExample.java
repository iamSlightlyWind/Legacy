import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestExample {
    @Test
    public void testAddNumbers() {
        Calculator calculator = new Calculator();
        int a = 5;
        int b = 3;
        int expected = 8;
        int result = calculator.addNumbers(a, b);
        assertEquals(expected, result);
    }
}

class Calculator {
    public int addNumbers(int a, int b) {
        return a + b;
    }
}