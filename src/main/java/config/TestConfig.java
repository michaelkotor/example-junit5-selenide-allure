package config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config.properties"})
public interface TestConfig extends Config {

    @Key("selenide.timeout")
    int timeout();

    @Key("selenide.browser")
    String browser();

    @Key("selenide.headless")
    boolean headless();

    @Key("selenide.browserSize")
    String browserSize();

    @Key("selenide.screenshots")
    boolean screenshots();

    @Key("selenide.savePageSource")
    boolean savePageSource();
}
