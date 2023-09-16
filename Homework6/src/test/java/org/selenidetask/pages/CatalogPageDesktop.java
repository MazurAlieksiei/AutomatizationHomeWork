package org.selenidetask.pages;

import org.selenidetask.desktop.elements.CatalogNavigationListForm;
import org.selenidetask.desktop.elements.QuickNavigationForm;

import java.util.List;

public class CatalogPageDesktop extends CatalogPage {

    private QuickNavigationForm quickNavigationForm = new QuickNavigationForm();

    private CatalogNavigationListForm catalogNavigationListForm = new CatalogNavigationListForm();

    public QuickNavigationForm getQuickNavigationForm() {
        return quickNavigationForm;
    }

    public CatalogNavigationListForm getCatalogNavigationListForm() {
        return catalogNavigationListForm;
    }

    public boolean isAsideListDisplayed() {
        return catalogNavigationListForm.isAsideListDisplayed();
    }

    public boolean isCatalogSelectionsExist(String sectionName) {
        return quickNavigationForm.isItemExist(sectionName);
    }

    public void clickOnItem(String navigationText) {
        quickNavigationForm.clickOnItem(navigationText);
    }

    public boolean isAsideListSectionsDisplayed(String elementName) {
        return catalogNavigationListForm.getAsideListElementForm().isAsideListElementDisplayed(elementName);
    }

    public void clickOnAsideListElement(String asideListElementName) {
        catalogNavigationListForm.getAsideListElementForm().clickOnAsideListElement(asideListElementName);
    }

    public boolean isAsideListElementDropdownDescriptionsExists() {
        List<String> descriptions = catalogNavigationListForm.getAsideListElementForm().getAsideListElementDropdownDescriptions();
        return isNotBlank(descriptions);
    }

    public boolean isAsideListElementDropdownTitlesExists() {
        List<String> titles = catalogNavigationListForm.getAsideListElementForm().getAsideListElementTitles();
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
}
