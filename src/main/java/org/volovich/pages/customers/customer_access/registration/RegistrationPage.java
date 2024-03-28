package org.volovich.pages.customers.customer_access.registration;

import org.volovich.pages.base.BasePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class RegistrationPage extends BasePage {
    private final ElementsCollection SOCIAL_TITLE_RADIO_BUTTONS_LIST = $$x("//label[@class='radio-inline']");
    private final SelenideElement FIRST_NAME_FIELD = $x("//input[@name='firstname']");
    private final SelenideElement LAST_NAME_FIELD = $x("//input[@name='lastname']");
    private final SelenideElement EMAIL_FIELD = $x("//input[@name='email']");
    private final SelenideElement PASSWORD_FIELD = $x("//input[@name='password']");
    private final SelenideElement BIRTHDAY_FIELD = $x("//input[@name='birthday']");
    private final ElementsCollection CONFIRMATION_CHECK_BOXES = $$x("//section[@class='register-form']//input[@type='checkbox']");
    private final SelenideElement SAVE_CUSTOMER_BUTTON = $x("//section[@class='register-form']//button[@type='submit']");

    public void chooseSocialTitleByName(String socialTitle) {
        SOCIAL_TITLE_RADIO_BUTTONS_LIST
                .shouldHave(size(2))
                .find(Condition.text(socialTitle))
                .click();
    }

    public void inputFirstName(String firstName) {
        FIRST_NAME_FIELD
                .shouldBe(Condition.visible)
                .setValue(firstName);
    }

    public void inputLastName(String lastName) {
        LAST_NAME_FIELD
                .shouldBe(Condition.visible)
                .setValue(lastName);
    }

    public void inputEmail(String email) {
        EMAIL_FIELD
                .shouldBe(Condition.visible)
                .setValue(email);
    }

    public void inputPassword(String password) {
        PASSWORD_FIELD
                .shouldBe(Condition.visible)
                .setValue(password);
    }

    public void inputBirthday(String birthday) {
        BIRTHDAY_FIELD
                .shouldBe(Condition.visible)
                .setValue(birthday);
    }

    public void checkConfirmationCheckBoxes() {
        CONFIRMATION_CHECK_BOXES
                .shouldHave(size(3))
                .forEach(checkBox -> {
                    checkBox.click();
                    checkBox.shouldBe(Condition.checked);
                });
    }

    public void clickSaveCustomerButton() {
        SAVE_CUSTOMER_BUTTON
                .shouldBe(Condition.visible)
                .click();
    }
}
