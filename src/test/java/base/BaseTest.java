package base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.sun.javafx.runtime.async.AbstractRemoteResource;
import config.TestConfig;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.remote.DesiredCapabilities;

@Execution(ExecutionMode.CONCURRENT)
public abstract class BaseTest {
    protected static final TestConfig config = ConfigFactory.create(TestConfig.class, System.getProperties());
    private static DesiredCapabilities capabilities;

    private static final String RUN_TYPE_PARAM = "runType";
    private static String runType = System.getProperty(RUN_TYPE_PARAM);

    @BeforeAll
    public static void config() {
        Configuration.screenshots = config.screenshots();
        Configuration.savePageSource = config.savePageSource();
        Configuration.timeout = config.timeout();
        Configuration.browserSize = config.browserSize();

        switch (runType) {
            case "local":
                Configuration.browser = config.browser();
                Configuration.headless = config.headless();
                break;
            case "remote":
                capabilities = new DesiredCapabilities();
                capabilities.setBrowserName(config.browser());
                capabilities.setCapability("enableVNC", config.enableVNC());
                Configuration.remote = config.remoteUrl();
                Configuration.browserCapabilities = capabilities;
                break;
        }
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
