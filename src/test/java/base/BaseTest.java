package base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.TestConfig;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@Execution(ExecutionMode.CONCURRENT)
public abstract class BaseTest {
    protected static final TestConfig config = ConfigFactory.create(TestConfig.class, System.getProperties());

    @BeforeAll
    public static void config() {
        Configuration.screenshots = config.screenshots();
        Configuration.savePageSource = config.savePageSource();
        Configuration.timeout = config.timeout();
        Configuration.browser = config.browser();
        Configuration.headless = config.headless();
        Configuration.browserSize = config.browserSize();
    }

    @BeforeEach
    public void setUp() {
        SelenideLogger.addListener("Allure", new AllureSelenide()
            .savePageSource(config.savePageSource())
                .screenshots(config.screenshots()));
    }

    @AfterEach
    public void end() {
        SelenideLogger.removeListener("Allure");
        Selenide.close();
    }


}
