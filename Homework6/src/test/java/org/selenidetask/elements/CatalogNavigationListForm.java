package org.selenidetask.elements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class CatalogNavigationListForm {

    private SelenideElement formLocator = $x("//*[(@class = 'catalog-navigation-list__category') and (@data-id ='2')]");

    private SelenideElement asideListLocator = formLocator.$x(".//*[@class = 'catalog-navigation-list__aside-list'][1]");

    private AsideListElementForm asideListElementForm = new AsideListElementForm(asideListLocator);

    public AsideListElementForm getAsideListElementForm() {
        return asideListElementForm;
    }

    public boolean isAsideListDisplayed() {
        return asideListLocator.isDisplayed();
    }

}
