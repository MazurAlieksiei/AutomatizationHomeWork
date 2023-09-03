package org.webdriver.onliner.elements;

import org.openqa.selenium.By;

public class ProductCardForm {

    private By productCardFormLocator = By.xpath("//*[contains(@class, 'product__group')]");
    private By titleLocator = By.xpath("//*[contains(@class, 'product__title')]//span");

    public By getProductCardFormLocator() {
        return productCardFormLocator;
    }

    public By getTitleLocator() {
        return titleLocator;
    }
}
