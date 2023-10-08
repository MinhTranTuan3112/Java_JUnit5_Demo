import org.junit.Test;
import org.junit.jupiter.api.Disabled;

public class TestDisabledDemo {
    @Disabled("Disable until bug #42 has been resolved")
    @Test
    void testWillBeSkipped() {

    }
    @Test
    void testWillBeExecuted() {

    }
}
