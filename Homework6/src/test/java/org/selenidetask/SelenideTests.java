package org.selenidetask;


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.chrome.ChromeOptions;
import org.selenidetask.pages.CatalogPage;
import org.selenidetask.pages.CatalogPageDesktop;
import org.selenidetask.pages.CatalogPageMobile;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import static com.codeborne.selenide.Selenide.*;

public class SelenideTests {

    private CatalogPage catalogPage;

    private static Properties testProperties;


    private List<String> asideListElementsName = Arrays.asList("Ноутбуки, компьютеры, мониторы", "Комплектующие", "Хранение данных", "Сетевое оборудование");

    static {
        System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
    }

    @BeforeClass
    public void init() throws IOException {
        testProperties = new Properties();
        testProperties.load(new FileInputStream("src/test/resources/project.properties"));
        Configuration.startMaximized = true;
        if (testProperties.getProperty("mobile").equals("true")) {
            Map<String, String> mobileEmulation = new HashMap<>();
            mobileEmulation.put("deviceName", "Samsung Galaxy S8+");

            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
            Configuration.browserCapabilities = chromeOptions;
        }
        if (testProperties.getProperty("remoteSelenium") != null) {
            Configuration.remote = testProperties.getProperty("remoteSelenium");
        }
    }

    @BeforeMethod
    public void openOnliner() {
        open("https://catalog.onliner.by/");
        if (testProperties.getProperty("mobile").equals("true")) {
            catalogPage = new CatalogPageMobile();
        } else {
            catalogPage = new CatalogPageDesktop();
        }
    }

    @AfterMethod
    public void cleanup() {
        WebDriverRunner.getWebDriver().close();
    }

    @DataProvider(name = "sectionsNames")
    public Object[][] createData() {
        return new Object[][]{
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

    @Test(dataProvider = "sectionsNames")
    public void testCatalogSelectionsPresenceDesktop(String sectionName, boolean expected) {
        Assert.assertEquals(catalogPage.isCatalogSelectionsExist(sectionName), expected, "No such element " + sectionName + " exist.");
    }

    @Test(priority = 2)
    public void testVerticalSectionsPresence() {
        SoftAssert softAssert = new SoftAssert();
        catalogPage.clickOnItem("Компьютеры и сети");
        //softAssert.assertTrue(catalogPage.isAsideListDisplayed(), "Aside list is not displayed.");

        for (String elementName : asideListElementsName) {
            softAssert.assertTrue(catalogPage.isAsideListSectionsDisplayed(elementName),
                    elementName + " is not displayed");
        }

        softAssert.assertAll();
    }


    @Test()
    public void testAsideListElementsContainsPriceAndCount() {
        SoftAssert softAssert = new SoftAssert();
        catalogPage.clickOnItem("Компьютеры и сети");
        //softAssert.assertTrue(catalogPage.isAsideListDisplayed(), "Aside list is not displayed.");
        softAssert.assertTrue(catalogPage.isAsideListSectionsDisplayed("Комплектующие"), asideListElementsName + " is not displayed.");
        catalogPage.clickOnAsideListElement("Комплектующие");
        softAssert.assertTrue(catalogPage.isAsideListElementDropdownDescriptionsExists(), "Description of elements is blank.");
        softAssert.assertTrue(catalogPage.isAsideListElementDropdownTitlesExists(), "Titles of elements is blank.");

        softAssert.assertAll();
    }
}
