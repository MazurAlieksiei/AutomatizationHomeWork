package org.cucumbertask.browser;

import org.apache.log4j.Logger;
import org.cucumbertask.enums.BrowserType;
import org.openqa.selenium.WebDriver;


public class Browser {

    private static Logger log = Logger.getLogger(Browser.class);
    private static WebDriver driver;
    private static BrowserType browserType;

    private Browser() {
    }

    public static void initDriver() {
        log.info("Инициализация драйвера");
        //browserType = BrowserType.valueOf(System.getProperty("browserType"));
        driver = BrowserFactory.createDriver(BrowserType.CHROME);
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    public static void close() {
        log.info("Закрытие браузера");
        if (driver != null) {
            driver.close();
            driver = null;
        }
    }

    public static BrowserType getBrowserType() {
        return browserType;
    }
}