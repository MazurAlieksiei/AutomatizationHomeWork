package org.webdriver.onliner.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.webdriver.onliner.utility.Browser;

public class QuickNavigationForm {

    private static final String ITEM_PATTERN = "//*[contains(text(),'%s')]";

    private static int WAIT_TIMEOUT = 10;

    public void clickOnItem(String navigationText) {
        getQuickNavigationElement(navigationText).click();
    }

    public boolean isItemExist(String navigationText) {
        return getQuickNavigationElement(navigationText).isDisplayed();
    }

    private WebElement getQuickNavigationElement(String navigationText) {
        String xpath = String.format(ITEM_PATTERN,navigationText);
        WebElement button = new WebDriverWait(Browser.getDriver(), WAIT_TIMEOUT)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        return button;
    }
}
