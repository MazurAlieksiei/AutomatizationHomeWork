package org.selenidetask;


import com.codeborne.selenide.WebDriverRunner;
import org.selenidetask.elements.CatalogNavigationListForm;
import org.selenidetask.elements.QuickNavigationForm;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class SelenideTests {

    QuickNavigationForm quickNavigationForm;
    CatalogNavigationListForm catalogNavigationListForm;



    private List<String> asideListElementsName = Arrays.asList("Ноутбуки, компьютеры, мониторы","Комплектующие","Хранение данных","Сетевое оборудование");

    static {
        System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
    }

    @BeforeMethod
    public void init(){
        quickNavigationForm = new QuickNavigationForm();
        catalogNavigationListForm = new CatalogNavigationListForm();
    }

    @AfterMethod
    public void cleanup() {
        WebDriverRunner.getWebDriver().close();
    }

    @DataProvider(name = "sectionsNames")
    public Object[][] createData() {
        return new Object[][] {
                {"Электроника", true},
                {"Компьютеры и сети", true},
                {"Бытовая техника", true},
                {"Стройка и ремонт", true},
                {"Дом и сад", true},
                {"Авто и мото", true},
                {"Красота и спорт", true},
                {"Детям и мамам", true},
                {"Работа и офис", false},
                {"Еда", false}
        };
    }
    @Test(dataProvider = "sectionsNames", groups = "firstCase")
    public void testCatalogSelectionsPresence (String sectionName, boolean expected) {
        open("https://catalog.onliner.by/");
        Assert.assertEquals(quickNavigationForm.isItemExist(sectionName),expected) ;
    }

    @Test
    public void testVerticalSectionsPresence () {
        open("https://catalog.onliner.by/");
        quickNavigationForm.clickOnItem("Компьютеры и сети");
        Assert.assertTrue(catalogNavigationListForm.isAsideListDisplayed());

        for (String elementName : asideListElementsName) {
            Assert.assertTrue(catalogNavigationListForm.getAsideListElementForm().isAsideListElementDisplayed(elementName));
        }
    }


    @Test
    public void testAsideListElementsContainsPriceAndCount() {
        open("https://catalog.onliner.by/");
        quickNavigationForm.clickOnItem("Компьютеры и сети");
        Assert.assertTrue(catalogNavigationListForm.isAsideListDisplayed());
        Assert.assertTrue(catalogNavigationListForm.getAsideListElementForm().isAsideListElementDisplayed("Комплектующие"));
        catalogNavigationListForm.getAsideListElementForm().clickOnAsideListElement("Комплектующие");
        List<String> descriptions = catalogNavigationListForm.getAsideListElementForm().getAsideListElementDropdownDescriptions();
        List<String> titles = catalogNavigationListForm.getAsideListElementForm().getAsideListElementTitles();
        Assert.assertTrue(isNotBlank(descriptions));
        Assert.assertTrue(isNotBlank(titles));

    }

    private boolean isNotBlank(List<String> elements) {
        for (String element : elements) {
            if (element.isBlank()) {
                return false;
            }
        }
        return true;
    }
}
