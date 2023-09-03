package org.webdriver.onliner.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.webdriver.onliner.elements.FilterForm;
import org.webdriver.onliner.elements.ProductCardForm;
import org.webdriver.onliner.enums.TopMenuItem;
import org.webdriver.onliner.utility.Browser;

import java.util.ArrayList;
import java.util.List;


public class ItemsInCatalogPage extends BasePage{

    private static final String PAGE_PATTERN = "//*[contains(@class, 'catalog')]" +
            "//*[contains(@class, 'header__title') and contains(text(), '%s')]";

    private By loaderLocator = By.xpath("//*[@id='schema-products' and contains(@class, 'processing')]");

    private static int WAIT_TIMEOUT = 4;

    private FilterForm filterForm = new FilterForm();

    private ProductCardForm productCardForm = new ProductCardForm();

    public FilterForm getFilterForm() {
        return filterForm;
    }

    @Override
    public void openPage() {
    }

    public boolean isOpened(String title) {
        String xpath = String.format(PAGE_PATTERN,title);
        return Browser.getDriver().findElement(By.xpath(xpath)).isDisplayed();
    }

    public void waitUntilCatalogUpdated() {
        new WebDriverWait(Browser.getDriver(), WAIT_TIMEOUT)
                .until(ExpectedConditions.invisibilityOfElementLocated(loaderLocator));
    }

    public List<String> getAllProductTitles() {
        List<WebElement> titleElements = Browser.getDriver().findElements(productCardForm.getTitleLocator());
        List<String> titlesText = new ArrayList<>();
        for (WebElement titleElement : titleElements) {
           titlesText.add(titleElement.getText());
        }
        return titlesText;
    }

}
