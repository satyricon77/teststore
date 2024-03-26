package products.pages;

import base.pages.BasePage;
import base.customconditions.CustomConditions;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import products.data.ProductsExpectedData;
import products.instances.Product;

import static com.codeborne.selenide.CollectionCondition.allMatch;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SearchResultPage extends BasePage {
    private final SelenideElement SORT_BY_DROP_DOWN = $x("//button[@data-toggle='dropdown']");
    private final ElementsCollection SORT_BY_DROP_DOWN_TYPES = $$x("//div[@class='dropdown-menu']//*[contains(@class, 'select-list')]");
    private ElementsCollection PRODUCT_CARDS = $$x("//div[@id='js-product-list']//article[contains(@class, 'product')]");

    public void clickSortByDropDown() {
        SORT_BY_DROP_DOWN.shouldBe(visible)
                .click();
    }

    public void chooseSortTypeByName(String sortType) {
        SORT_BY_DROP_DOWN_TYPES.shouldHave(CollectionCondition.sizeGreaterThan(1))
                .findBy(text(sortType)).click();
    }

    private String getProductName(SelenideElement productCard) {
        return productCard.$x(".//*[contains(@class, 'product-title')]")
                .shouldBe(visible)
                .text();
    }

    private String getProductPrice(SelenideElement productCard) {
        return productCard.$x(".//*[contains(@class, 'product-price')]")
                .shouldBe(visible)
                .text()
                .split("\\$")[1]; // Parse price to get only number
    }

    private Double parseProductPrice(String price) {
        try {
            return Double.parseDouble(price);
        } catch (NumberFormatException nfe) {
            throw new AssertionError("Can't parse price: " + price);
        }
    }

    public boolean isProductsSorted(ProductsExpectedData.SortingType sortingType) {
        PRODUCT_CARDS.should(allMatch("All products on products search result page should be visible", card -> card.isDisplayed()));

        boolean isSortedCorrectly = false;
        for (int cardNumber = 0; cardNumber < PRODUCT_CARDS.size() - 1; cardNumber++) {
            PRODUCT_CARDS.get(cardNumber).shouldBe(CustomConditions.immobile()); //wait until element is not moving
            Product currentProduct = new Product(getProductName(PRODUCT_CARDS.get(cardNumber)), getProductPrice(PRODUCT_CARDS.get(cardNumber)));
            Product nextProduct = new Product(getProductName(PRODUCT_CARDS.get(cardNumber + 1)), getProductPrice(PRODUCT_CARDS.get(cardNumber + 1)));

            isSortedCorrectly = switch (sortingType) {
                case A_TO_Z -> currentProduct.getProductName().compareToIgnoreCase(nextProduct.getProductName()) <= 0;
                case Z_TO_A -> currentProduct.getProductName().compareToIgnoreCase(nextProduct.getProductName()) >= 0;
                case PRICE_LOW_TO_HIGH ->
                        parseProductPrice(currentProduct.getProductPrice()) <= parseProductPrice(nextProduct.getProductPrice());
                case PRICE_HIGH_TO_LOW ->
                        parseProductPrice(currentProduct.getProductPrice()) >= parseProductPrice(nextProduct.getProductPrice());
            };
        }
        return isSortedCorrectly;
    }

    public void clickProductByItsName(String expectedProductName) {
        PRODUCT_CARDS.shouldBe(CustomConditions.visible());

        for (SelenideElement productCard : PRODUCT_CARDS) {
            String actualProductName = getProductName(productCard);
            if (actualProductName.equalsIgnoreCase(expectedProductName)) {
                productCard.click();
                break;
            }
        }
    }
}
