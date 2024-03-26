package products.tests;

import base.tests.BasicTest;
import home.pages.HomePage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import products.instances.ExpectedProduct;
import products.data.ProductsDataProvider;
import products.data.ProductsExpectedData;
import products.instances.Product;
import products.pages.CheckoutPage;
import products.pages.ProductPage;
import products.pages.SearchResultPage;
import products.pages.ShoppingCartPage;
import utils.Verify;

import java.text.SimpleDateFormat;

import static base.pages.BasePage.faker;
import static base.pages.BasePage.navigateToPage;
import static customer.customeraccess.data.registration.RegisterCustomerExpectedData.SOCIAL_TITLE;
import static products.data.CheckoutPageExpectedData.*;
import static products.data.ProductsExpectedData.MUG_PRODUCT_TITLE;
import static products.data.ProductsExpectedData.T_SHIRT_PRODUCT_TITLE;
import static report.ExtentManager.log;

public class ProductTest extends BasicTest {
    private HomePage homePage;
    private SearchResultPage searchResultPage;
    private ProductPage productPage;
    private ProductPage.ShoppingCartModalWindow shoppingCartModalWindow;
    private ExpectedProduct expectedProduct = new ExpectedProduct();
    private ShoppingCartPage shoppingCartPage;
    private CheckoutPage checkoutPage;
    private CheckoutPage.PersonalInformationCheckoutStep personalInformationCheckOutStep;
    private CheckoutPage.AddressCheckoutStep addressCheckOutStep;
    private CheckoutPage.ShippingCheckoutStep shippingCheckOutStep;
    private CheckoutPage.PaymentCheckoutStep paymentCheckOutStep;

    @BeforeClass
    public void initPages() {
        homePage = new HomePage();
        searchResultPage = new SearchResultPage();
        productPage = new ProductPage();
        shoppingCartModalWindow = productPage.new ShoppingCartModalWindow();
        shoppingCartPage = new ShoppingCartPage();
        checkoutPage = new CheckoutPage();
        personalInformationCheckOutStep = new CheckoutPage.PersonalInformationCheckoutStep();
        addressCheckOutStep = new CheckoutPage.AddressCheckoutStep();
        shippingCheckOutStep = new CheckoutPage.ShippingCheckoutStep();
        paymentCheckOutStep = new CheckoutPage.PaymentCheckoutStep();
    }

    @Test(priority = 1, dataProvider = "sortingTypes", dataProviderClass = ProductsDataProvider.class,
            description = "Given a user on a webpage with product listings,\n" +
                    "When they input a search term and choose a sorting type from a dropdown,\n" +
                    "Then the products should be sorted according to the selected sorting type.")
    public void checkProductSortingTest(ProductsExpectedData.SortingType sortingType) {
        log("Starting checkProductSortingTest...");
        navigateToPage(BASE_URL);

        homePage.inputSearchBox(MUG_PRODUCT_TITLE);
        searchResultPage.clickSortByDropDown();
        searchResultPage.chooseSortTypeByName(sortingType.getSortingType());

        log("Checking that products are sorted according to: " + sortingType.getSortingType() + " sorting type...");
        Verify.verifyTrue(searchResultPage.isProductsSorted(sortingType),
                "All products on product search result page are sorted in accordance with: " + sortingType.getSortingType() + " sorting type",
                "Products on product search result page are not sorted in accordance with: " + sortingType.getSortingType() + " sorting type");
    }

    @Test(priority = 2, description = "Given a user's interaction with a website to purchase a product,\n" +
            "When they navigate, search, select, and customize the product," +
            "Then they proceed through checkout steps including personal information, address, shipping method, and payment,\n" +
            "Finally, the system confirms the order with a message.")
    public void buyProductTest() {
        log("Starting buyProductTest...");
        navigateToPage(BASE_URL);

        homePage.inputSearchBox(T_SHIRT_PRODUCT_TITLE);
        searchResultPage.clickProductByItsName(T_SHIRT_PRODUCT_TITLE);

        log("Checking product info on product page...");
        Product productOnProductPage = productPage.getProduct();
        Verify.verifyEquals(productOnProductPage, expectedProduct.getProductDetails(),
                "Product has expected content: " + expectedProduct.getProductDetails(),
                "Not expected product content found on product page: " + productOnProductPage);

        productPage.clickAddToCartButton();

        Product productOnShoppingCartModalWindow = shoppingCartModalWindow.getProduct();
        log("Checking already customized product in shopping-cart modal window on product page...");
        Verify.verifyEquals(productOnShoppingCartModalWindow, expectedProduct.getCustomizedProduct(),
                "Product has expected content: " + expectedProduct.getCustomizedProduct(),
                "Not expected product content found in shopping-cart modal window on product page: " + productOnShoppingCartModalWindow);

        shoppingCartModalWindow.clickCloseModalWindowButton();
        homePage.clickShoppingCartIcon();

        log("Checking already customized product on shopping-cart page...");
        Product productOnShoppingCartPage = shoppingCartPage.getShoppingCartProduct();
        Verify.verifyEquals(productOnShoppingCartPage, expectedProduct.getCustomizedProduct(),
                "Product has expected content: " + expectedProduct.getCustomizedProduct(),
                "Not expected product content found on shopping-cart page: " + productOnShoppingCartPage);

        shoppingCartPage.clickProceedToCheckoutButton();
        //#1 PERSONAL INFORMATION checkout step
        checkoutPage.waitCheckoutStep(CheckoutStep.PERSONA_INFORMATION.getStepName());
        personalInformationCheckOutStep.chooseSocialTitleByName(SOCIAL_TITLE);
        personalInformationCheckOutStep.inputFirstName(faker.name().firstName());
        personalInformationCheckOutStep.inputLastName(faker.name().lastName());
        personalInformationCheckOutStep.inputEmail(faker.internet().emailAddress());
        personalInformationCheckOutStep.inputPassword(faker.internet().password());
        personalInformationCheckOutStep.inputBirthday(new SimpleDateFormat("MM/dd/yyyy").format(faker.date().birthday()));
        personalInformationCheckOutStep.checkConfirmationCheckBoxes();
        personalInformationCheckOutStep.clickContinueButton();

        //#2 ADDRESS checkout step
        checkoutPage.waitCheckoutStep(CheckoutStep.ADDRESS.getStepName());
        addressCheckOutStep.inputAddressField(faker.address().fullAddress());
        addressCheckOutStep.inputCityField(faker.address().city());
        addressCheckOutStep.chooseValueInStateDropdown(faker.address().state());
        addressCheckOutStep.inputZipPostalCode(String.valueOf(faker.number().digits(5)));
        addressCheckOutStep.clickContinueButton();

        //#3 SHIPPING METHOD checkout step
        checkoutPage.waitCheckoutStep(CheckoutStep.SHIPPING_METHOD.getStepName());
        shippingCheckOutStep.clickContinueButton();

        //#4 PAYMENT checkout step
        checkoutPage.waitCheckoutStep(CheckoutStep.PAYMENT.getStepName());
        paymentCheckOutStep.choosePaymentOptionByItsName(PAYMENT_OPTION_NAME);
        paymentCheckOutStep.clickConsentCheckBox();
        paymentCheckOutStep.clickPlaceOrderButton();

        String confirmedOrderMessage = checkoutPage.getOrderConfirmedMessage();
        log("Checking that order is confirmed by the system...");
        Verify.verifyEquals(confirmedOrderMessage, ORDER_CONFIRMED_MESSAGE,
                "Buy product workflow works as expected, order's confirmed",
                "Not expected message about confirmed order: " + confirmedOrderMessage + ", something went wrong");
    }
}
