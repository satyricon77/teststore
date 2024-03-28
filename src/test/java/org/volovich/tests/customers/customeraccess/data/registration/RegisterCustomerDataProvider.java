package org.volovich.tests.customers.customeraccess.data.registration;

import org.testng.annotations.DataProvider;

import java.text.SimpleDateFormat;

import static org.volovich.pages.base.BasePage.faker;

public class RegisterCustomerDataProvider {
    @DataProvider
    public Object[][] getUserRegistrationData() {
        return new Object[][]{
                {RegisterCustomerExpectedData.SOCIAL_TITLE, faker.name().firstName(), faker.name().lastName(),
                        new SimpleDateFormat("MM/dd/yyyy").format(faker.date().birthday())},
        };
    }
}
