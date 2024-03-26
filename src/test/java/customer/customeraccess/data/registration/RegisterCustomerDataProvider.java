package customer.customeraccess.data.registration;

import org.testng.annotations.DataProvider;

import java.text.SimpleDateFormat;

import static base.pages.BasePage.faker;
import static customer.customeraccess.data.registration.RegisterCustomerExpectedData.SOCIAL_TITLE;

public class RegisterCustomerDataProvider {
    @DataProvider
    public Object[][] getUserRegistrationData() {
        return new Object[][]{
                {SOCIAL_TITLE, faker.name().firstName(), faker.name().lastName(),
                        new SimpleDateFormat("MM/dd/yyyy").format(faker.date().birthday())},
        };
    }
}
