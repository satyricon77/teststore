package products.pages;

import base.pages.BasePage;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import products.instances.Product;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public class ProductPage extends BasePage {
    private final SelenideElement PRODUCT_FRAME = $x("//div[contains(@class, 'product-container')]");
    private final SelenideElement ADD_TO_CART_BUTTON = $x("//button[@data-button-action='add-to-cart']");

    private String getProductTitle() {
        return PRODUCT_FRAME.$x(".//h1")
                .shouldBe(visible)
                .text();
    }

    private String getProductPrice() {
        return PRODUCT_FRAME.$x(".//span[contains(@class, 'current-price-value')]")
                .shouldBe(visible)
                .text();
    }

    private List<String> getProductSizes() {
        return PRODUCT_FRAME.$x(".//select[contains(@class, 'form-control-select')]")
                .shouldBe(interactable).$$x(".//option").texts();
    }

    private List<String> getProductColors() {
        return PRODUCT_FRAME.$$x(".//input[@class='input-color']")
                .shouldHave(CollectionCondition.sizeGreaterThanOrEqual(1))
                .attributes("title");
    }

    private String getProductQuantity() {
        return PRODUCT_FRAME.$(By.id("quantity_wanted"))
                .shouldBe(visible)
                .getValue();
    }

    public Product getProduct() {
        Product product = new Product();
        product.setProductName(getProductTitle())
                .setProductPrice(getProductPrice())
                .setProductOptions(new ArrayList<>() {{
                    addAll(getProductSizes());
                    addAll(getProductColors());
                    add(getProductQuantity());
                }});
        return product;
    }

    public void clickAddToCartButton() {
        ADD_TO_CART_BUTTON.shouldBe(visible)
                .click();
    }

    //implemented as inner class because always created along with product page object
    public class ShoppingCartModalWindow {
        private final SelenideElement MODAL_SHOPPING_CART_WINDOW = $x("//div[@id='blockcart-modal']");

        private String getProductTitle() {
            return MODAL_SHOPPING_CART_WINDOW.$x(".//*[contains(@class, 'product-name')]")
                    .shouldBe(visible)
                    .text();
        }

        private String getProductPrice() {
            return MODAL_SHOPPING_CART_WINDOW.$x(".//p[contains(@class, 'product-price')]")
                    .shouldBe(visible)
                    .text();
        }

        private String getProductSize() {
            return MODAL_SHOPPING_CART_WINDOW.$x(".//span[@class= 'size']//strong")
                    .shouldBe(visible)
                    .text();
        }

        private String getProductColor() {
            return MODAL_SHOPPING_CART_WINDOW.$x(".//span[@class= 'color']//strong")
                    .shouldBe(visible)
                    .text();
        }

        private String getProductQuantity() {
            return MODAL_SHOPPING_CART_WINDOW.$x(".//span[@class= 'product-quantity']//strong")
                    .shouldBe(visible)
                    .text();
        }

        public Product getProduct() {
            Product product = new Product();
            product.setProductName(getProductTitle())
                    .setProductPrice(getProductPrice())
                    .setProductOptions(new ArrayList<>() {{
                        add(getProductSize());
                        add(getProductColor());
                        add(getProductQuantity());
                    }});
            return product;
        }

        public void clickCloseModalWindowButton() {
            MODAL_SHOPPING_CART_WINDOW.$x(".//button[@class='close']")
                    .shouldBe(clickable)
                    .click();
        }
    }
}
