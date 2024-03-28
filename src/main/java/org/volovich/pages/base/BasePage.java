package org.volovich.pages.base;

import com.github.javafaker.Faker;
import org.volovich.utils.report.ExtentManager;

import static com.codeborne.selenide.Selenide.open;


public class BasePage {
    public static Faker faker = new Faker();

    public BasePage() {
    }

    public static void navigateToPage(String url) {
        ExtentManager.log("Navigating to URL: " + url);
        open(url);
    }
}
