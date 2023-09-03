package org.webdriver.onliner.utility;

import org.openqa.selenium.*;
import org.openqa.selenium.logging.LogEntry;
import org.webdriver.onliner.enums.BrowserType;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;


public class Browser {

    private static WebDriver driver;

    private static BrowserType browserType;

    private Browser() {
    }

    public static void initDriver() {
        browserType = BrowserType.valueOf(System.getProperty("browserType"));
        driver = BrowserFactory.createDriver(browserType);
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

    public static void printLogs() {
        Set<String> logs = driver.manage().logs().get("browser").getAll().stream()
                .map(LogEntry::getMessage).collect(Collectors.toSet());
        logs.forEach(System.out::println);
    }

    public static void saveScreenShot() throws IOException {

        File screenShots = new File("./test-output/screenshots");

        if (!screenShots.exists()) {
            screenShots.mkdirs();
        }
        Date date = new Date();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy-h-mm-ss-SS--a");
        String formattedDate = simpleDateFormat.format(date);
        String fileName = browserType.name() + "_" + formattedDate + "screenshot.png";

        byte[] scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        Files.write(new File("./test-output/screenshots/" + fileName).toPath(), scrFile, StandardOpenOption.CREATE);
    }

    /**
     * Метод перемещения области видимости к элементу.
     *
     * @param element Элемент, к которому перемещаемся.
     */
    public static void moveToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
