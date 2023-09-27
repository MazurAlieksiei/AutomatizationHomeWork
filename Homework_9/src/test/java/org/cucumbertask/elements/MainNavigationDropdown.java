package org.cucumbertask.elements;

import org.apache.log4j.Logger;
import org.cucumbertask.browser.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import java.util.List;

public class MainNavigationDropdown {

    private static Logger log = Logger.getLogger(MainNavigationDropdown.class);
    private By navigationDropdownLocator = By.xpath("//*[contains(@class, 'b-main-navigation__dropdown_visible')]/div");
    private String NAVIGATION_DROPDOWN_COLUMN_PATTERN = "//*[contains(@class, 'dropdown-title-link') and contains(text(), '%s')]" +
            "/ancestor::*[contains(@class, 'b-main-navigation__dropdown-column')]";
    private By navigationDropdownAdvertListLocator = By.xpath(".//*[contains(@class, 'dropdown-advert-sign')]");

    public boolean isExists() {
        log.info("Проверка, что элемент подменю отображается.");
        return Browser.getDriver().findElement(navigationDropdownLocator).isDisplayed();
    }

    public List<WebElement> getNavigationDropdownAdvertListElements(String columnName) {
        log.info("Получение списка элементов у навигационной панели для" + columnName + ".");
        String parentElementXpath = String.format(NAVIGATION_DROPDOWN_COLUMN_PATTERN, columnName);

        List<WebElement> advertListElements = Browser.getDriver().findElement(By.xpath(parentElementXpath))
                .findElements(navigationDropdownAdvertListLocator);

        return advertListElements;
    }
}