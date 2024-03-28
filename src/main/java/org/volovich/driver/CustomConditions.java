package org.volovich.driver;

import com.codeborne.selenide.WebElementCondition;
import com.codeborne.selenide.WebElementsCondition;

public class CustomConditions {
    public static WebElementCondition immobile() {
        return new Immobile();
    }

    public static WebElementsCondition visible() {
        return new ElementsVisible();
    }
}
