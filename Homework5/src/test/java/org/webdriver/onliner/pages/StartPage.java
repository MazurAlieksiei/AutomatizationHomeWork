package org.webdriver.onliner.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.webdriver.onliner.elements.QuickNavigationForm;
import org.webdriver.onliner.utility.Browser;

public class StartPage extends BasePage{

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
    @Override
    public void openPage() {
        Browser.getDriver().get("https://www.onliner.by/");
    }
}
