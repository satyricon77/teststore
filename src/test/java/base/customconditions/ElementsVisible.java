package base.customconditions;

import com.codeborne.selenide.CheckResult;
import com.codeborne.selenide.Driver;
import com.codeborne.selenide.WebElementsCondition;
import org.openqa.selenium.WebElement;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import java.util.List;

public class ElementsVisible extends WebElementsCondition {
    @Nonnull
    @CheckReturnValue
    public CheckResult check(Driver driver, List<WebElement> elements) {
        boolean isVisible = elements.stream().allMatch(WebElement::isDisplayed);
        return new CheckResult(isVisible, isVisible ? "visible" : "hidden");
    }

    @Override
    public String toString() {
        return "all elements are visible";
    }
}
