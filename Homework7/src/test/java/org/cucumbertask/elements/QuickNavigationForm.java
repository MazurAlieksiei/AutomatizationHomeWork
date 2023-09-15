package org.cucumbertask.elements;

import org.cucumbertask.browser.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class QuickNavigationForm {

    private static final String ITEM_PATTERN = "//*[contains(text(),'%s') and @class = 'b-main-navigation__text']";

    private static int WAIT_TIMEOUT = 10;

    public void moveToQuickNavigationElement(String navigationText) {
        new Actions(Browser.getDriver()).
                moveToElement(getQuickNavigationElement(navigationText)).build().perform();
    }

    public boolean isItemExist(String navigationText) {
        return getQuickNavigationElement(navigationText).isDisplayed();
    }

    private WebElement getQuickNavigationElement(String navigationText) {
        String xpath = String.format(ITEM_PATTERN, navigationText);
        WebElement button = new WebDriverWait(Browser.getDriver(), WAIT_TIMEOUT)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        return button;
    }
}
