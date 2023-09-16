package org.selenidetask.desktop.elements;

import com.codeborne.selenide.SelenideElement;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;

public class AsideListElementForm {
    private SelenideElement asideListElement;
    private String asideListElementLocator = ".//*[@class = 'catalog-navigation-list__aside-title' and contains(text(), '%s')]";

    private String asideListElementDropdownDescriptionLocator = "//*[contains(@class, 'catalog-navigation-list__aside-item_active')]//*[contains(@class, 'dropdown-description')]";

    private String getAsideListElementDropdownTitleLocator = "//*[contains(@class, 'catalog-navigation-list__aside-item_active')]//*[contains(@class, 'dropdown-title')]";

    public AsideListElementForm(SelenideElement asideListElement) {
        this.asideListElement = asideListElement;
    }

    public SelenideElement getAsideListElement(String asideListElementName) {
        String xpath = String.format(asideListElementLocator, asideListElementName);
        SelenideElement button = asideListElement.$x(xpath);
        return button;
    }

    public boolean isAsideListElementDisplayed(String asideListElementName) {
        return getAsideListElement(asideListElementName).isDisplayed();
    }

    public void clickOnAsideListElement(String asideListElementName) {
        getAsideListElement(asideListElementName).click();
    }

    public List<String> getAsideListElementDropdownDescriptions() {
        List<SelenideElement> elements;
        elements = $$x(asideListElementDropdownDescriptionLocator);

        List<String> descriptions = new ArrayList<>();
        for (SelenideElement element : elements) {
            descriptions.add(element.getText());
        }
        return descriptions;
    }

    public List<String> getAsideListElementTitles() {
        List<SelenideElement> elements;
        elements = $$x(getAsideListElementDropdownTitleLocator);

        List<String> titles = new ArrayList<>();
        for (SelenideElement element : elements) {
            titles.add(element.getText());
        }
        return titles;
    }
}
