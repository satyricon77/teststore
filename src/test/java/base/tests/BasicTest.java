package base.tests;

import base.tests.listeners.ATTestListener;
import com.codeborne.selenide.testng.TextReport;
import webdriverprovider.MyWDProvider;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import utils.PropertyLoader;

@Listeners({ATTestListener.class, TextReport.class})
public class BasicTest {
    public static final boolean HEADLESS_MODE_PROP = Boolean.parseBoolean(PropertyLoader.loadProperty("at.headless"));
    public static final String BASE_URL = PropertyLoader.loadProperty("base.URL");
    @BeforeClass
    public void setUp() {
        Configuration.headless = HEADLESS_MODE_PROP;
        Configuration.browser = MyWDProvider.class.getName();
    }
}
