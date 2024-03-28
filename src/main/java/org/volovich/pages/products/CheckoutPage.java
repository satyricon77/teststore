package org.volovich.pages.products;

import org.volovich.pages.base.BasePage;
import base.customconditions.CustomConditions;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CheckoutPage extends BasePage {
    private final SelenideElement CURRENT_CHECKOUT_STEP = $x("//section[contains(@class, 'checkout-step -current')]//h1[contains(@class, 'step-title')]");
    private final SelenideElement ORDER_CONFIRMED_MESSAGE = $x("//section[@id='content-hook_order_confirmation']//h3[contains(@class, 'card-title')]");

    public void waitCheckoutStep(String checkoutStep) {
        //upon navigating through different steps UI is moving
        CURRENT_CHECKOUT_STEP.shouldBe(CustomConditions.immobile()).shouldHave(text(checkoutStep), Duration.ofSeconds(15));
    }

    public String getOrderConfirmedMessage() {
        return ORDER_CONFIRMED_MESSAGE.shouldBe(visible)
                .getOwnText().trim();
    }

    public static class PersonalInformationCheckoutStep {
        private final ElementsCollection SOCIAL_TITLE_RADIO_BUTTONS_LIST = $$x("//label[@class='radio-inline']");
        private final SelenideElement FIRST_NAME_FIELD = $x("//input[@name='firstname']");
        private final SelenideElement LAST_NAME_FIELD = $x("//input[@name='lastname']");
        private final SelenideElement EMAIL_FIELD = $x("//input[@name='email']");
        private final SelenideElement PASSWORD_FIELD = $x("//input[@name='password']");
        private final SelenideElement BIRTHDAY_FIELD = $x("//input[@name='birthday']");
        private final ElementsCollection CONFIRMATION_CHECK_BOXES = $$x("//span[@class='custom-checkbox']//input[@type='checkbox']");
        private final SelenideElement CONTINUE_BUTTON = $x("//button[@data-link-action='register-new-customer']");

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

        public void clickContinueButton() {
            CONTINUE_BUTTON.shouldBe(visible)
                    .click();
        }
    }

    public static class AddressCheckoutStep {
        private final SelenideElement ADDRESS_FIELD = $(By.id("field-address1"));
        private final SelenideElement CITY_FIELD = $(By.id("field-city"));
        private final SelenideElement ZIP_POSTAL_CODE_FIELD = $(By.id("field-postcode"));
        private final SelenideElement STATE_DROP_DOWN = $(By.id("field-id_state"));
        private final SelenideElement CONTINUE_BUTTON = $(By.name("confirm-addresses"));

        public void inputAddressField(String address) {
            ADDRESS_FIELD.shouldBe(visible)
                    .setValue(address);
        }

        public void inputCityField(String city) {
            CITY_FIELD.shouldBe(visible)
                    .setValue(city);
        }

        public void inputZipPostalCode(String zipPostalCode) {
            ZIP_POSTAL_CODE_FIELD.shouldBe(visible)
                    .setValue(zipPostalCode);
        }

        public void chooseValueInStateDropdown(String value) {
            STATE_DROP_DOWN.shouldBe(visible)
                    .selectOption(value);
        }

        public void clickContinueButton() {
            CONTINUE_BUTTON.shouldBe(visible)
                    .click();
        }
    }

    public static class ShippingCheckoutStep {
        private final SelenideElement CONTINUE_BUTTON = $(By.name("confirmDeliveryOption"));

        public void clickContinueButton() {
            CONTINUE_BUTTON.shouldBe(visible)
                    .click();
        }
    }

    public static class PaymentCheckoutStep {
        private final SelenideElement CONSENT_CHECK_BOX = $(By.id("conditions_to_approve[terms-and-conditions]"));
        private final ElementsCollection PAYMENT_OPTIONS = $$x("//label[contains(@for, 'payment-option')]");
        private final SelenideElement PLACE_ORDER_BUTTON = $x("//div[@id='payment-confirmation']//button[@type='submit']");

        public void clickConsentCheckBox() {
            CONSENT_CHECK_BOX.shouldNotBe(checked)
                    .click();
        }

        public void choosePaymentOptionByItsName(String paymentOptionName) {
            PAYMENT_OPTIONS.shouldHave(sizeGreaterThanOrEqual(1))
                    .find(exactText(paymentOptionName))
                    .click();
        }

        public void clickPlaceOrderButton() {
            PLACE_ORDER_BUTTON.shouldBe(visible)
                    .click();
        }
    }
}
