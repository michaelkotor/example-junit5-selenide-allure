import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestClass {
    @BeforeAll
    public static void config() {
        Configuration.screenshots = true;
        Configuration.savePageSource = false;
        Configuration.timeout = 10000;
        Configuration.browser = "chrome";
        Configuration.headless = false;
        Configuration.browserSize = "1920x1080";
    }

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
