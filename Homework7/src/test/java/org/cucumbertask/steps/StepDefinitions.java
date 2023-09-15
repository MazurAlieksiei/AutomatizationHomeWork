package org.cucumbertask.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.cucumbertask.browser.Browser;
import org.cucumbertask.elements.MainNavigationDropdown;
import org.cucumbertask.elements.QuickNavigationForm;
import org.cucumbertask.pages.StartPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class StepDefinitions {

    private StartPage startPage;

    private QuickNavigationForm quickNavigationForm;
    private MainNavigationDropdown mainNavigationDropdown;


    @Before
    public void openOnliner() {
        Browser.getDriver().get("https://www.onliner.by/");
        startPage = new StartPage();
        quickNavigationForm = new QuickNavigationForm();
        mainNavigationDropdown = new MainNavigationDropdown();
    }

    @After
    public void cleanup() {
        Browser.close();
    }

    @Given("открыта главная страница онлайнера")
    public void открыта_главная_страница_онлайнера() {
        startPage.openPage();
        Assertions.assertTrue(startPage.isOpened());
    }

    @When("пользователь наводится мышкой на пункт меню {string}")
    public void пользовательНаводитсяМышкойНаПунктМеню(String navigationText) {
        Assertions.assertTrue(quickNavigationForm.isItemExist(navigationText));
        quickNavigationForm.moveToQuickNavigationElement(navigationText);
    }

    @Then("подменю открывается")
    public void подменюОткрывается() {
        Assertions.assertTrue(mainNavigationDropdown.isExists());
    }

    @And("в подменю {string} доступны пункты")
    public void вПодменюДоступныПункты(String columnName, List<String> elements) {
        Assertions.assertTrue(isDropdownAdvertListElementsExist
                (mainNavigationDropdown.getNavigationDropdownAdvertListElements(columnName), elements));
    }

    private boolean isDropdownAdvertListElementsExist(List<WebElement> advertListElements, List<String> elements) {
        List<String> advertListElementTitles = new ArrayList<>();
        if (advertListElements == null) {
            return false;
        } else {
            for (WebElement advertListElement : advertListElements) {
                advertListElementTitles.add(advertListElement.getText());
            }
        }

        if (!advertListElementTitles.equals(elements)) {
            return false;
        }
        return true;
    }

}
