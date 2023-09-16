package org.selenidetask.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.selenidetask.mobile.elements.AsideListForm;

import java.util.List;

import static com.codeborne.selenide.Selenide.$x;

public class CatalogPageMobile extends CatalogPage {

    private static final SelenideElement catalogQuickNavigationLocator = $x("//ul[@class='catalog-navigation-classifier ']");

    private String sectionTitleLocator = ".//span[@class='catalog-navigation-classifier__item-title']/span";
    private SelenideElement asideListLocator = $x("//*[@class = 'catalog-navigation-list__aside-list'][1]");

    AsideListForm asideListForm = new AsideListForm();

    public boolean isCatalogSelectionsExist(String sectionName) {
        if (getCatalogNavigationElement(sectionName) == null) {
            return false;
        }

        return getCatalogNavigationElement(sectionName).isDisplayed();
    }

    public void clickOnItem(String navigationText) {
        getCatalogNavigationElement(navigationText).click();
    }

    public boolean isAsideListDisplayed() {
        return asideListLocator.isDisplayed();
    }

    public boolean isAsideListSectionsDisplayed(String elementName) {
        return asideListForm.isAsideListElementDisplayed(elementName);
    }

    public void clickOnAsideListElement(String asideListElementName) {
        asideListForm.clickOnAsideListElement(asideListElementName);
    }

    public boolean isAsideListElementDropdownDescriptionsExists() {
        List<String> descriptions = asideListForm.getAsideListElementDropdownDescriptions();
        return isNotBlank(descriptions);
    }

    public boolean isAsideListElementDropdownTitlesExists() {
        List<String> titles = asideListForm.getAsideListElementTitles();
        return isNotBlank(titles);
    }

    /**
     * Метод проверки наличия элементов массива.
     *
     * @param elements Массив(лист) строк.
     * @return Возвращает true, если элемент в массиве не isBlank, иначе возвращает false.
     */
    private boolean isNotBlank(List<String> elements) {
        for (String element : elements) {
            if (element.isBlank()) {
                return false;
            }
        }
        return true;
    }

    private SelenideElement getCatalogNavigationElement(String sectionName) {
        List<SelenideElement> elements;
        elements = catalogQuickNavigationLocator.$$(By.xpath(sectionTitleLocator));
        for (SelenideElement element : elements) {
            String texts = element.getText();
            if (texts.equals(sectionName)) {
                return element;
            }
        }
        return null;
    }
}
