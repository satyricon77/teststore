package customer.customeraccess.pages.login;

import base.pages.BasePage;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage extends BasePage {
    private final SelenideElement LOGIN_FORM = $x("//section[@class='login-form']");

    public void clickCreateAccountHyperLink() {
        $x("//div[@class='no-account']//*[@*='display-register-form']").shouldBe(visible)
                .click();
    }

    public void inputEmail(String expectedEmail) {
        LOGIN_FORM.$x(".//input[@name='email']")
                .shouldBe(visible)
                .setValue(expectedEmail);
    }

    public void inputPassword(String expectedPassword) {
        LOGIN_FORM.$x(".//input[@name='password']")
                .shouldBe(visible)
                .setValue(expectedPassword);
    }

    public void clickSignInButton() {
        LOGIN_FORM.$x(".//button[@id='submit-login']")
                .shouldBe(visible)
                .click();
    }

    public void clickForgotYourPasswordHyperLink() {
        LOGIN_FORM.$x(".//div[@class='forgot-password']/a")
                .shouldBe(visible)
                .click();
    }

    public String getAuthenticationFailedValidationErrorMessage() {
        return LOGIN_FORM.$x(".//li[contains(@class, 'alert-danger')]")
                .shouldBe(visible)
                .text();
    }
}
