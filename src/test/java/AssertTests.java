import org.hamcrest.core.CombinableMatcher;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import java.util.Arrays;
public class AssertTests {
    @Test
    public void testAssertArrayEquals() {
        byte[] expected = "trial".getBytes();
        byte[] actual = "trial".getBytes();
        assertArrayEquals("failure - byte arrays not same", expected, actual);
    }
    @Test
    public void testAssertEquals() {
        assertEquals("failure - string are not equal", "text", "text");
    }
    @Test
    public void testAssertFalse() {
        assertFalse("failure - should be false", false);
    }
    @Test
    public void testAssertNotNull() {
        assertNotNull("should not be null", new Object());
    }
    @Test
    public void testAssertNotSame() {
        assertNotSame("should not be same Object", new Object(), new Object());
    }
    @Test
    public void testAssertNull() {
        assertNull("should be null", null);
    }
    @Test
    public void testAssertSame() {
        Integer aNumber = Integer.valueOf(768);
        assertSame("should be same", aNumber, aNumber);
    }

    //JUnit Matches assertThat
    @Test
    public void testAssertThatBothContainsString() {
        assertThat("albumen", both(containsString("a")).and(containsString("b")));
    }
    @Test
    public void testAssertThatHasItems() {
        assertThat(Arrays.asList("one","two","three"), hasItems("one","three"));
    }
    @Test
    public void testAssertThatEveryItemContainStrings() {
        assertThat(Arrays.asList(new String[]{"fun","ban","net"}), everyItem(containsString("n")));
    }
    @Test
    public void testAssertThatHamcrestCoreMatches() {
        assertThat("good", allOf(equalTo("good"), startsWith("good")));
        assertThat("good", not(allOf(equalTo("bad"), equalTo("good"))));
        assertThat("good", anyOf(equalTo("bad"), equalTo("good")));
        assertThat(7, not(CombinableMatcher.<Integer>either(equalTo(3)).or(equalTo(4))));
        assertThat(new Object(), not(sameInstance(new Object())));
    }
    @Test
    public void testAssertTrue() {
        assertTrue("failure - should be true", true);
    }
}
