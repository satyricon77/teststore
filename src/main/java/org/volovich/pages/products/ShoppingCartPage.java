package org.volovich.pages.products;

import com.codeborne.selenide.SelenideElement;
import org.volovich.model.products.Product;

import java.util.ArrayList;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class ShoppingCartPage {
    private final SelenideElement SHOPPING_CART_ITEM_BOX = $x("//li[@class='cart-item']");
    private final SelenideElement PROCEED_TO_CHECKOUT_BUTTON = $x("//div[contains(@class, 'checkout')]//*[@class = 'btn btn-primary']");

    private String getProductTitle() {
        return SHOPPING_CART_ITEM_BOX
                .shouldBe(visible)
                .$x(".//div[@class='product-line-info']")
                .text();
    }

    private String getProductPrice() {
        return SHOPPING_CART_ITEM_BOX
                .shouldBe(visible)
                .$x(".//span[@class='price']")
                .text();
    }

    private String getProductSize() {
        return SHOPPING_CART_ITEM_BOX
                .shouldBe(visible)
                .$x(".//div[contains(@class, 'size')]/span[@class='value']")
                .text();
    }

    private String getProductColor(){
        return SHOPPING_CART_ITEM_BOX
                .shouldBe(visible)
                .$x(".//div[contains(@class, 'color')]/span[@class='value']")
                .text();
    }

    private String getProductQuantity(){
        return SHOPPING_CART_ITEM_BOX
                .shouldBe(visible)
                .$x(".//input[contains(@class, 'product-quantity')]")
                .getValue();
    }

    public Product getShoppingCartProduct(){
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

    public void clickProceedToCheckoutButton(){
        PROCEED_TO_CHECKOUT_BUTTON
                .shouldBe(visible)
                .click();
    }
}
