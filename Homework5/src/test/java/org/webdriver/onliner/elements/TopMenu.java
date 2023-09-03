package org.webdriver.onliner.elements;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.webdriver.onliner.enums.TopMenuItem;
import org.webdriver.onliner.utility.Browser;

public class TopMenu {

    private static final String ITEM_PATTERN = "//nav//*[contains(text(),'%s')]";

    private static int WAIT_TIMEOUT = 20;

    public void clickOnItem(TopMenuItem item) {
        getMenuElement(item).click();
    }

    public boolean isItemExist(TopMenuItem topMenuItem) {
        return getMenuElement(topMenuItem).isDisplayed();
    }

    private WebElement getMenuElement(TopMenuItem topMenuItem) {
        String xpath = String.format(ITEM_PATTERN, topMenuItem.getValue());
        WebElement menuItem = new WebDriverWait(Browser.getDriver(), WAIT_TIMEOUT)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        return menuItem;
    }
}
