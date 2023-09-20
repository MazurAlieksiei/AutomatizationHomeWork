package org.selenidetask.pages;

public abstract class CatalogPage {

    public abstract boolean isCatalogSelectionsExist(String sectionName);

    public abstract void clickOnItem(String navigationText);

    public abstract boolean isAsideListDisplayed();

    public abstract boolean isAsideListSectionsDisplayed(String elementName);

    public abstract void clickOnAsideListElement(String asideListElementName);

    public abstract boolean isAsideListElementDropdownDescriptionsExists();

    public abstract boolean isAsideListElementDropdownTitlesExists();
}
