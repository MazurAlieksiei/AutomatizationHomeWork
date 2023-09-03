package org.webdriver.onliner.pages;

import org.webdriver.onliner.elements.TopMenu;

public abstract class BasePage {
    public abstract void openPage();

    protected TopMenu topMenu = new TopMenu();
}
