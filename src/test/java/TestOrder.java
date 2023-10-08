import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(OrderAnnotation.class)
public class TestOrder {
    @Test
    @Order(3)
    public void testA2() {
        System.out.println("testA2");
        assertTrue(true);
    }

    @Test
    @Order(1)
    public void testA() {
        System.out.println("testA");
        assertTrue(true);
    }

    @Test
    @Order(2)
    public void testA1() {
        System.out.println("testA1");
        assertTrue(true);
    }
}