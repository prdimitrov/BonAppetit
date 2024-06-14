package com.bonappetit.model.enums;

public enum CategoryName {
    MAIN_DISH("MAIN_DISH"),
    DESSERT("DESSERT"),
    COCKTAIL("COCKTAIL");

    private final String displayName;

    CategoryName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
