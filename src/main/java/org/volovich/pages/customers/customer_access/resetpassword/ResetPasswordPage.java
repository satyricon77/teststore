package org.volovich.pages.customers.customer_access.resetpassword;

import org.volovich.pages.base.BasePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class ResetPasswordPage extends BasePage {
    private final SelenideElement FORGOT_PASSWORD_FORM = $x("//*[@class='forgotten-password']");

    public void inputEmail(String email) {
        FORGOT_PASSWORD_FORM.$x(".//input[@id='email']")
                .shouldBe(Condition.visible)
                .setValue(email);
    }

    public void clickSendResetLinkButton() {
        FORGOT_PASSWORD_FORM.$x(".//button[@id='send-reset-link']")
                .shouldBe(Condition.visible)
                .click();
    }

    public String getSuccessfulResetPasswordMessage() {
        return $x("//ul[@class='ps-alert-success']//p")
                .shouldBe(Condition.visible)
                .text();
    }
}
