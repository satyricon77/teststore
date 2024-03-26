package customer.customeraccess.pages.resetpassword;

import base.pages.BasePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class ResetPasswordPage extends BasePage {
    private final SelenideElement FORGOT_PASSWORD_FORM = $x("//*[@class='forgotten-password']");
    private final SelenideElement SUCCESS_RESET_PASSWORD_MESSAGE = $x("//ul[@class='ps-alert-success']//p");

    public void inputEmail(String email) {
        FORGOT_PASSWORD_FORM
                .$x(".//input[@id='email']")
                .shouldBe(Condition.visible)
                .setValue(email);
    }

    public void clickSendResetLinkButton() {
        FORGOT_PASSWORD_FORM
                .$x(".//button[@id='send-reset-link']")
                .shouldBe(Condition.visible)
                .click();
    }

    public String getSuccessfulResetPasswordMessage() {
        return SUCCESS_RESET_PASSWORD_MESSAGE
                .shouldBe(Condition.visible)
                .text();
    }
}
