package base.pages;

import com.github.javafaker.Faker;

import static com.codeborne.selenide.Selenide.open;
import static report.ExtentManager.log;

public class BasePage {
    public static Faker faker = new Faker();

    public BasePage() {
    }

    public static void navigateToPage(String url) {
        log("Navigating to URL: " + url);
        open(url);
    }
}
