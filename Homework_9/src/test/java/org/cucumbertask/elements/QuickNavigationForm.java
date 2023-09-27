package org.cucumbertask.elements;

import org.apache.log4j.Logger;
import org.cucumbertask.browser.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class QuickNavigationForm {

    private static Logger log = Logger.getLogger(QuickNavigationForm.class);
    private static final String ITEM_PATTERN = "//*[contains(text(),'%s') and @class = 'b-main-navigation__text']";

    private static int WAIT_TIMEOUT = 10;

    public void moveToQuickNavigationElement(String navigationText) {
        log.info("Перемещаем курсор к элементу " + navigationText + " на панели быстрого доступа.");
        new Actions(Browser.getDriver()).
                moveToElement(getQuickNavigationElement(navigationText)).build().perform();
    }

    public boolean isItemExist(String navigationText) {
        log.info("Проверяем, что элемент " + navigationText + " отображается на панели быстрого доступа.");
        return getQuickNavigationElement(navigationText).isDisplayed();
    }

    private WebElement getQuickNavigationElement(String navigationText) {
        log.info("Ожидаем отображения и получения элемента " + navigationText);
        String xpath = String.format(ITEM_PATTERN, navigationText);
        WebElement button = null;
        try {
            button = new WebDriverWait(Browser.getDriver(), WAIT_TIMEOUT)
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        } catch (TimeoutException ex) {
            log.error("Элемент на панели быстрого доступа " + navigationText + " не найден." + ex.getMessage());
        }

        return button;
    }
}