package org.cucumbertask.pages;

import org.cucumbertask.browser.Browser;
import org.cucumbertask.elements.QuickNavigationForm;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;


public class StartPage {

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

        try {
            Browser.getDriver().findElement(startPageLocator);
        } catch (NoSuchElementException ex) {
            return false;
        }

        return true;
    }

    public void openPage() {
        Browser.getDriver().get("https://www.onliner.by/");
    }
}
