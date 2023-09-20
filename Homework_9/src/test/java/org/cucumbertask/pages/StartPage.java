package org.cucumbertask.pages;

import org.apache.log4j.Logger;
import org.cucumbertask.browser.Browser;
import org.cucumbertask.elements.QuickNavigationForm;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;


public class StartPage {

    private static Logger log = Logger.getLogger(StartPage.class);
    private By startPageLocator = By.xpath("//*[contains(text(), 'main-page')]");
    private QuickNavigationForm quickNavigationForm = new QuickNavigationForm();

    public QuickNavigationForm getQuickNavigationForm() {
        return quickNavigationForm;
    }

    /**
     * Метод проверки отображения стартовой страницы.
     *
     * @return True - страница открыта. False - страница не открыта.
     */
    public boolean isOpened() {
        log.info("Проверяем, отрыта ли стартовая страница.");
        try {
            Browser.getDriver().findElement(startPageLocator);
        } catch (NoSuchElementException ex) {
            log.error("Стартовая страница не отображается.");
            return false;
        }
        log.info("Стартовая страница отображается.");
        return true;
    }

    public void openPage() {
        log.info("Открытие стартовой страницы.");
        Browser.getDriver().get("https://www.onliner.by/");
    }
}
