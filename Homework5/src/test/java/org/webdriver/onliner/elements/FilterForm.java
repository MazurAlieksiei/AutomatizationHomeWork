package org.webdriver.onliner.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.webdriver.onliner.utility.Browser;

public class FilterForm {

    private By filterFormLocator = By.xpath("//*[@id='schema-filter']");

    private static final String FILTER_BLOCK_BY_TITLE_PATTERN = "//*[contains(@class, 'filter__label')]/*[contains(text(), '%s')]" +
            "//ancestor::*[contains(@class, 'filter__fieldset')]";

    private static final String FILTER_CHECKBOX_VALUE_PATTERN = "//*[contains(@class, 'filter__checkbox-text') and contains(text(), '%s')]";

    public boolean isExists() {
        return Browser.getDriver().findElement(filterFormLocator).isDisplayed();
    }

    public void chooseValueForSpecifiedFilter(String filterTitleName, String valueToSelect) {
        String filterBlockXpath = String.format(FILTER_BLOCK_BY_TITLE_PATTERN, filterTitleName);
        String filterCheckboxValueXpath = String.format(FILTER_CHECKBOX_VALUE_PATTERN, valueToSelect);
        WebElement filterBlockElement = Browser.getDriver().findElement(By.xpath(filterBlockXpath));
        Browser.moveToElement(filterBlockElement);
        WebElement filterCheckboxElement = filterBlockElement.findElement(By.xpath(filterCheckboxValueXpath));

        filterCheckboxElement.click();
    }
}
