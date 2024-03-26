package base.customconditions;

import com.codeborne.selenide.CheckResult;
import com.codeborne.selenide.Driver;
import com.codeborne.selenide.WebElementCondition;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;

import static com.codeborne.selenide.Selenide.sleep;

public class Immobile extends WebElementCondition {

    protected Immobile() {
        super("immobile");
    }

    @Nonnull
    @Override
    public CheckResult check(Driver driver, WebElement webElement) {
        boolean isImmobile;
        Point initialLocation = webElement.getLocation();
        sleep(1000);

        // Check if element's location remains unchanged
        Point currentLocation = webElement.getLocation();
        isImmobile = initialLocation != currentLocation;

        return new CheckResult(isImmobile, isImmobile ? "immobile" : "movable");
    }

    @Override
    public String toString() {
        return "immobile";
    }
}
