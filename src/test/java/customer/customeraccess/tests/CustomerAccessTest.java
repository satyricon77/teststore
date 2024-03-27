package customer.customeraccess.tests;

import base.tests.BasicTest;
import customer.customeraccess.data.login.LoginExpectedData;
import customer.customeraccess.data.registration.RegisterCustomerExpectedData;
import customer.customeraccess.data.resetpassword.ResetPasswordExpectedData;
import customer.customeraccess.pages.resetpassword.ResetPasswordPage;
import home.pages.HomePage;
import customer.customeraccess.pages.login.LoginPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import customer.customeraccess.data.registration.RegisterCustomerDataProvider;
import customer.customeraccess.pages.registration.RegistrationPage;
import utils.Verify;

import static base.pages.BasePage.*;
import static report.ExtentManager.log;

public class CustomerAccessTest extends BasicTest {
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private HomePage homePage;
    private ResetPasswordPage resetPasswordPage;
    private RegisterCustomerExpectedData registerCustomerExpectedData = new RegisterCustomerExpectedData();
    private ResetPasswordExpectedData resetPasswordExpectedData = new ResetPasswordExpectedData();
    private LoginExpectedData loginExpectedData = new LoginExpectedData();
    private String userEmail = registerCustomerExpectedData.generateEmailForCustomerRegistration();
    private String userPassword = registerCustomerExpectedData.generatePasswordForCustomerRegistration();

    @BeforeTest
    public void initEnvironment() {
        loginPage = new LoginPage();
        registrationPage = new RegistrationPage();
        homePage = new HomePage();
        resetPasswordPage = new ResetPasswordPage();
    }

    //additional libraries can be used such as: Faker. Data provider is used just for demonstration purposed
    @Test(priority = 1, dataProvider = "getUserRegistrationData", dataProviderClass = RegisterCustomerDataProvider.class,
            description = "Given a user on the sign up form," +
                    " when they register with provided data including social title, first name, last name, and birthday" +
                    ", then user should be logged to a newly created account right away")
    public void checkCustomerRegistrationFlowTest(String socialTitle, String firstName, String lastName, String birthday) {
        log("Starting checkCustomerRegistrationFlowTest...");
        navigateToPage(BASE_URL);

        homePage.clickSignInButton();
        loginPage.clickCreateAccountHyperLink();
        registrationPage.chooseSocialTitleByName(socialTitle);
        registrationPage.inputFirstName(firstName);
        registrationPage.inputLastName(lastName);
        registrationPage.inputEmail(userEmail);
        registrationPage.inputPassword(userPassword);
        registrationPage.inputBirthday(birthday);
        registrationPage.checkConfirmationCheckBoxes();
        registrationPage.clickSaveCustomerButton();

        log("Checking if customer is logged in after sign up flow...");
        Verify.verifyTrue(homePage.isUserLoggedIn(firstName + " " + lastName),
                "Customer is successfully logged in after sign up flow",
                "Customer is not logged in after sign up flow");
    }

    @Test(priority = 2, dependsOnMethods = "checkCustomerRegistrationFlowTest",
            description = "Given a user on the homepage, after completing the customer registration flow," +
                    " when they attempt to log in with valid credentials," +
                    " then user should be logged in")
    public void checkCustomerLoginFlowWithValidCredentialsTest() {
        log("Starting checkCustomerLoginFlowWithValidCredentialsTest...");
        navigateToPage(BASE_URL);

        homePage.clickSignInButton();
        loginPage.inputEmail(userEmail);
        loginPage.inputPassword(userPassword);
        loginPage.clickSignInButton();

        log("Checking if customer can be logged in using valid credentials...");
        Verify.verifyTrue(homePage.isUserLoggedIn(), "Customer is successfully logged in",
                "Customer is not logged in using valid credentials");
    }

    @Test(priority = 3, description = "Given a user on the homepage," +
            " when they attempt to log in with invalid credentials," +
            " then validation error should appear which indicates to invalid data provided.")
    public void checkCustomerLoginFlowWithInvalidCredentialsTest() {
        log("Starting checkCustomerLoginFlowWithInvalidCredentialsTest...");
        navigateToPage(BASE_URL);

        homePage.clickSignInButton();
        loginPage.inputEmail(faker.internet().emailAddress());
        loginPage.inputPassword(faker.internet().password());
        loginPage.clickSignInButton();

        log("Checking validation error message upon logging in with invalid credentials...");
        String invalidCredentialsValidMessage = loginPage.getAuthenticationFailedValidationErrorMessage();

        Verify.verifyEquals(invalidCredentialsValidMessage,
                loginExpectedData.getAuthenticationFailedValidationErrorMessage(),
                "Expected validation error message for invalid credentials is present: " +
                        invalidCredentialsValidMessage,
                "Not expected validation error message for logging in with invalid credentials: " +
                        invalidCredentialsValidMessage);
    }

    @Test(priority = 4, description = "Given a user on the reset password page" +
            ", when they initiate the forgot password flow by providing their email address," +
            " a reset password link should be sent to user's mail box and success message appears")
    public void checkForgotPasswordFlowTest() {
        log("Starting checkForgotPasswordFlowTest...");
        navigateToPage(BASE_URL);

        String emailForPasswordReset = faker.internet().emailAddress();
        homePage.clickSignInButton();
        loginPage.clickForgotYourPasswordHyperLink();
        resetPasswordPage.inputEmail(emailForPasswordReset);
        resetPasswordPage.clickSendResetLinkButton();

        log("Checking successful reset password message...");
        String successfulResetPasswordMessage = resetPasswordPage.getSuccessfulResetPasswordMessage();

        Verify.verifyEquals(successfulResetPasswordMessage,
                resetPasswordExpectedData.getSuccessfulResetPasswordMessage(emailForPasswordReset),
                "Expected successful reset password message is present: " + successfulResetPasswordMessage,
                "Not expected message after trying to reset a password: " + successfulResetPasswordMessage);
    }

    @AfterMethod
    public void handleUserState() {
        homePage.signOutFromLoggedInUser();
    }
}
