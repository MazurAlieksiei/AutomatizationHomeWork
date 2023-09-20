package org.webdriver.onliner.enums;

public enum TopMenuItem {
    CATALOG("Каталог"),
    NEWS("Новости"),
    AUTOSALES(""),
    FLATSALES(""),
    SERVICES("");

    private String value;
    TopMenuItem(String value){
      this.value = value;
    }

    public String getValue() {
        return value;
    }
}
