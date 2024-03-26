package webdriverprovider;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.annotation.Nonnull;

import static base.tests.BasicTest.HEADLESS_MODE_PROP;

public class MyWDProvider implements WebDriverProvider {

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        ChromeOptions options = getChromeOptions();
        options.merge(capabilities);
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        return driver;
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();

        if (HEADLESS_MODE_PROP) {
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("--window-size=1920,1080");
        }

        return chromeOptions;
    }
}
