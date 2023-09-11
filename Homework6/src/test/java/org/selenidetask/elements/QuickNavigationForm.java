package org.selenidetask.elements;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.$x;

public class QuickNavigationForm {

    private static final SelenideElement  catalogQuickNavigationLocator = $x("//ul[@class='catalog-navigation-classifier ']");

    private String sectionTitleLocator = ".//*[contains(@class,'tem-title-wrapper')]";



    public boolean isItemExist(String navigationText) {
        if (getQuickNavigationElement(navigationText) == null) {
            return false;
        }

        return getQuickNavigationElement(navigationText).isDisplayed();
    }

     public void clickOnItem(String navigationText) {
           getQuickNavigationElement(navigationText).click();
       }

    private SelenideElement getQuickNavigationElement(String navigationText){
        List<SelenideElement> elements;
        elements = catalogQuickNavigationLocator.$$(By.xpath(sectionTitleLocator));
        for (SelenideElement element : elements) {
            String texts = element.getText();
            if (texts.equals(navigationText)){
                return element;
            }
        }
        return null;
    }
}
