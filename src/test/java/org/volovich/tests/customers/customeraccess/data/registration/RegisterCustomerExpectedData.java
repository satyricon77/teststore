package org.volovich.tests.customers.customeraccess.data.registration;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterCustomerExpectedData {
    public static String SOCIAL_TITLE = "Mr.";

    public String generateEmailForCustomerRegistration() {
        String formatPattern = new SimpleDateFormat("HHmmss").format(new Date());
        return "test" + formatPattern + "email@gmail.com";
    }

    public String generatePasswordForCustomerRegistration() {
        String formatPattern = new SimpleDateFormat("HHmmss").format(new Date());
        return "test" + formatPattern + "password";
    }
}
