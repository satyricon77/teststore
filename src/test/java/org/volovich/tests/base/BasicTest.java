package org.volovich.tests.base;

import org.volovich.AppConstants;
import org.volovich.listeners.ATTestListener;
import com.codeborne.selenide.testng.TextReport;
import org.volovich.driver.MyWDProvider;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.volovich.utils.PropertyLoader;

@Listeners({ATTestListener.class, TextReport.class})
public class BasicTest {
    @BeforeClass
    public void setUp() {
        Configuration.headless = AppConstants.HEADLESS_MODE_PROP;
        Configuration.browser = MyWDProvider.class.getName();
    }
}
