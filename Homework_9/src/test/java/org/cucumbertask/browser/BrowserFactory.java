package org.cucumbertask.browser;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.cucumbertask.enums.BrowserType;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class BrowserFactory {
    static {
        System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
        System.setProperty("webdriver.gecko.driver", "c:/geckodriver.exe");
    }

    private static Logger log = Logger.getLogger(BrowserFactory.class);

    public static boolean isLogsEnabled = true;

    public static WebDriver createDriver(BrowserType browserType) {
        log.info("Создание драйвера " + browserType);
        WebDriver driver;
        switch (browserType) {
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();

                if (isLogsEnabled) {
                    LoggingPreferences preferences = new LoggingPreferences();
                    preferences.enable(LogType.BROWSER, Level.ALL);
                    chromeOptions.setCapability(CapabilityType.LOGGING_PREFS, preferences);
                }
                driver = new ChromeDriver(chromeOptions);
                log.info("Создан драйвер CHROME.");
                break;
            case FIREFOX:
                driver = new FirefoxDriver();
                log.info("Создан драйвер FIREFOX.");
                break;
            default:
                log.fatal("Драйвер не создан.");
                throw new IllegalStateException("Browser Not Supported: " + browserType);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return driver;
    }
}
