import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestClass {
    @Test
    public void testPass() {
        Assertions.assertTrue(true);
    }

    @Test
    public void testFail() {
        Assertions.assertTrue(false);
    }
}
