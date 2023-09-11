package org.selenidetask;


import com.codeborne.selenide.WebDriverRunner;
import org.selenidetask.elements.CatalogNavigationListForm;
import org.selenidetask.elements.QuickNavigationForm;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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
    @Test(dataProvider = "sectionsNames", priority = 1)
    public void testCatalogSelectionsPresence (String sectionName, boolean expected) {
        open("https://catalog.onliner.by/");
        Assert.assertEquals(quickNavigationForm.isItemExist(sectionName),expected, "No such element " + sectionName + " exist.") ;
    }

    @Test(priority = 2)
    public void testVerticalSectionsPresence () {
        SoftAssert softAssert = new SoftAssert();
        open("https://catalog.onliner.by/");
        quickNavigationForm.clickOnItem("Компьютеры и сети");
        softAssert.assertTrue(catalogNavigationListForm.isAsideListDisplayed(), "Aside list is not displayed.");

        for (String elementName : asideListElementsName) {
            softAssert.assertTrue(catalogNavigationListForm.getAsideListElementForm().isAsideListElementDisplayed(elementName),
                    elementName + " is not displayed");
        }

        softAssert.assertAll();
    }


    @Test(priority = 3)
    public void testAsideListElementsContainsPriceAndCount() {
        SoftAssert softAssert = new SoftAssert();
        open("https://catalog.onliner.by/");
        quickNavigationForm.clickOnItem("Компьютеры и сети");
        softAssert.assertTrue(catalogNavigationListForm.isAsideListDisplayed(), "Aside list is not displayed.");
        softAssert.assertTrue(catalogNavigationListForm.getAsideListElementForm()
                        .isAsideListElementDisplayed("Комплектующие"),asideListElementsName + " is not displayed.");
        catalogNavigationListForm.getAsideListElementForm().clickOnAsideListElement("Комплектующие");
        List<String> descriptions = catalogNavigationListForm.getAsideListElementForm().getAsideListElementDropdownDescriptions();
        List<String> titles = catalogNavigationListForm.getAsideListElementForm().getAsideListElementTitles();
        softAssert.assertTrue(isNotBlank(descriptions), "Description of elements is blank.");
        softAssert.assertTrue(isNotBlank(titles), "Titles of elements is blank.");

        softAssert.assertAll();
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
