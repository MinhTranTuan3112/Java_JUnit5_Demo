import org.junit.Test;
import org.minhtran.PasswordValidator;
import org.hamcrest.core.CombinableMatcher;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import java.util.Arrays;
public class TestPasswordValidator {
    @Test
    public void testIsPasswordStrong() throws Exception {
        PasswordValidator pv = new PasswordValidator();
        boolean isStrong = pv.isPasswordStrong("12345678");
        assertTrue(isStrong);
    }
    @Test
    public void testIsPasswordNotStrong() throws Exception {
        var pv = new PasswordValidator();
        boolean isStrong = pv.isPasswordStrong("1234");
        assertFalse(isStrong);
    }
    @Test
    public void testIsPasswordTooLong() throws Exception {
        var pv = new PasswordValidator();
        assertThrows(Exception.class, () -> pv.isPasswordStrong("123456vhvjhjhfhj"));
    }
}
