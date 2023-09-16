package org.selenidetask.mobile.elements;

import com.codeborne.selenide.SelenideElement;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class AsideListForm {

    private String asideListElementLocator = "//div[@class='catalog-navigation-list__aside-item']/div[contains(text(), '%s')]";

    private String getAsideListElementDropdownTitleLocator = "//*[contains(@class, 'catalog-navigation-list__aside-item_active')]//*[contains(@class, 'dropdown-title')]";

    private String asideListElementDropdownDescriptionLocator = "//*[contains(@class, 'catalog-navigation-list__aside-item_active')]//*[contains(@class, 'dropdown-description')]";

    public SelenideElement getAsideListElement(String asideListElementName) {
        String xpath = String.format(asideListElementLocator, asideListElementName);
        SelenideElement button = $x(xpath);
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

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

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
