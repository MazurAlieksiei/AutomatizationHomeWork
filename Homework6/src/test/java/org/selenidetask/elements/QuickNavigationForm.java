package org.selenidetask.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$x;

public class QuickNavigationForm {

    private static final SelenideElement  catalogQuickNavigationLocator = $x("//ul[@class='catalog-navigation-classifier ']");

   // private String sectionLocator = ".//li//span[contains(text(),'%s')]";

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
        List<SelenideElement> elements = new ArrayList<>();
        elements = catalogQuickNavigationLocator.$$(By.xpath(sectionTitleLocator));
        for (SelenideElement element : elements) {
            String test = element.getText();
            if (test.equals(navigationText)){
                return element;
            }
        }
        return null;
    }
}
