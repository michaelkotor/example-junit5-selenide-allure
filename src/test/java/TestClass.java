import base.BaseTest;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class TestClass extends BaseTest {

    @Test
    public void testPass() {
        Assertions.assertTrue(true);
    }

    @Test
    public void testFail() {
        Assertions.assertTrue(false);
    }

    @Test
    public void selenideElementExistsPass() {
        Selenide.open("http://google.com");
        SelenideElement selenideElement = Selenide.element("div");
        Assertions.assertNotNull(selenideElement);
    }

    @Test
    public void selenideElementIsNotFoundFail() {
        Selenide.open("https://google.com");
        SelenideElement selenideElement = Selenide.element("michael");
        Assertions.assertTrue(selenideElement.exists());
    }

    @RepeatedTest(5)
    @Test
    public void selenideElementErrorBeforeAssertFail() {
        Selenide.open("https://google.com");
        SelenideElement selenideElement = Selenide.element("michael");
        selenideElement.click();
        Assertions.assertTrue(true);
    }


    @Test
    public void selenideElementErrorBeforeErrorAssertFail() {
        Selenide.open("https://google.com");
        SelenideElement selenideElement = Selenide.element("michael");
        if(true) {
            throw new RuntimeException();
        }
        selenideElement.click();
        Assertions.assertTrue(true);
    }
}
