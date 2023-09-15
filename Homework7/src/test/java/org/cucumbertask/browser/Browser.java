package org.cucumbertask.browser;

import org.cucumbertask.enums.BrowserType;
import org.openqa.selenium.WebDriver;


public class Browser {

    private static WebDriver driver;

    private static BrowserType browserType;

    private Browser() {
    }

    public static void initDriver() {
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
        if (driver != null) {
            driver.close();
            driver = null;
        }
    }

    public static BrowserType getBrowserType() {
        return browserType;
    }
}
