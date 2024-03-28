package org.volovich.tests.customers.customeraccess.data.resetpassword;

public class ResetPasswordExpectedData {
    public String getSuccessfulResetPasswordMessage(String email) {
        return String.format("If this email address has been registered in our store," +
                " you will receive a link to reset your password at %s.", email);
    }
}
