package utils;

import org.testng.Assert;
import report.ExtentManager;

import static org.testng.AssertJUnit.fail;
import static report.ExtentManager.log;
import static report.ExtentManager.pass;

public class Verify {
    public static void verifyEquals(Object actual, Object expected, String passMessage, String failureMessage) {
        try {
            Assert.assertEquals(actual, expected, passMessage);
            pass(passMessage);
        } catch (AssertionError error) {
            Assert.fail(failureMessage);
        }
    }

    public static void verifyTrue(boolean condition, String passMessage, String failureMessage){
        try{
            Assert.assertTrue(condition, passMessage);
            pass(passMessage);
        } catch (AssertionError error){
            Assert.fail(failureMessage);
        }
    }
}
