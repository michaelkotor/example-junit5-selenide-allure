import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@Execution(ExecutionMode.CONCURRENT)
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

    @BeforeEach
    public void add() {
        SelenideLogger.addListener("Allure", new AllureSelenide().screenshots(true).savePageSource(false));
    }

    @AfterEach
    public void delete() {
       SelenideLogger.removeListener("Allure");
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
