package org.webdriver.onliner.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.webdriver.onliner.pages.ItemsInCatalogPage;
import org.webdriver.onliner.pages.StartPage;
import org.webdriver.onliner.utility.Browser;

import java.util.List;

public class FilterTest extends BaseTest {

    StartPage startPage;
    ItemsInCatalogPage itemsInCatalogPage;


    @BeforeMethod
    public void openOnliner(){
        Browser.getDriver().get("https://www.onliner.by/");
        startPage = new StartPage();
        itemsInCatalogPage = new ItemsInCatalogPage();
    }



    @Test
    public void filterProductShouldWork() {
        Assert.assertTrue(startPage.isOpened());
        Assert.assertTrue(startPage.getQuickNavigationForm().isItemExist("Телевизоры"));

        startPage.getQuickNavigationForm().clickOnItem("Телевизоры");
        Assert.assertTrue(itemsInCatalogPage.isOpened("Телевизоры"));
        Assert.assertTrue(itemsInCatalogPage.getFilterForm().isExists());

        itemsInCatalogPage.getFilterForm().chooseValueForSpecifiedFilter("Производитель", "LG");
        itemsInCatalogPage.waitUntilCatalogUpdated();

        List<String> titles = itemsInCatalogPage.getAllProductTitles();
        Assert.assertTrue(isAllInValueToSelect("LG", titles));


    }

    @AfterMethod
    public void cleanup(){
        Browser.close();
    }

    private boolean isAllInValueToSelect(String valueToSelect,  List<String> titles) {
        for (String title : titles) {
            if (!title.contains(valueToSelect)){
                return false;
            }
        }

        return true;
    }
}

