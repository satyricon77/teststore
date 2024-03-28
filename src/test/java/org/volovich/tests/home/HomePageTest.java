package org.volovich.tests.home;

import org.volovich.tests.base.BasicTest;
import org.volovich.utils.home.HomePageExpectedData;
import org.volovich.model.home_page.BannerCard;
import org.volovich.model.home_page.FooterColumn;
import org.volovich.model.home_page.PopularProductCard;
import org.volovich.pages.home.HomePage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.volovich.utils.Verify;

import java.util.List;

import static org.volovich.AppConstants.BASE_URL;
import static org.volovich.pages.base.BasePage.navigateToPage;
import static org.volovich.utils.home.HomePageExpectedData.Banner.getBannersContent;
import static org.volovich.utils.home.HomePageExpectedData.PopularProduct.getPopularProducts;
import static org.volovich.utils.report.ExtentManager.log;

public class HomePageTest extends BasicTest {
    private HomePage homePage;
    private HomePageExpectedData homePageExpectedData = new HomePageExpectedData();

    @BeforeTest
    public void initEnvironment() {
        homePage = new HomePage();
    }

    @Test(priority = 1, description = "Given a user on a webpage," +
            "When they navigate to the homepage," +
            "Then the header menu tabs should match the expected tabs.")
    public void checkHeaderMenuTabsTest() {
        log("Starting checkHeaderMenuTabsTest...");
        navigateToPage(BASE_URL);

        List<List<String>> actualHeaderMenuTabContents = homePage.getHeaderMenuTabContents();
        List<List<String>> expectedHeaderMenuTabContents = homePageExpectedData.getHeaderMenuTabContents();

        log("Checking header tabs...");
        Verify.verifyEquals(actualHeaderMenuTabContents, expectedHeaderMenuTabContents,
                "Expected header tabs are present: " + expectedHeaderMenuTabContents,
                "Not expected header tabs are present: " + actualHeaderMenuTabContents);
    }

    @Test(priority = 2, description = "Given a user on a webpage," +
            "When they navigate to the homepage," +
            "Then the content of the marketing banners should match the expected content.")
    public void checkAdBannersTest() {
        log("Starting checkAdBannersTest...");
        navigateToPage(BASE_URL);

        List<BannerCard> actualBannersContent = homePage.getBannersContent();

        log("Checking marketing banners...");
        Verify.verifyEquals(actualBannersContent, getBannersContent(),
                "Expected marketing banners are present: " + getBannersContent(),
                "Not expected marketing banners are present: " + actualBannersContent);
    }

    @Test(priority = 3, description = "Given a user on a webpage," +
            "When they navigate to the homepage," +
            "Then the items in the 'Popular Products' section should match the expected items.")
    public void checkPopularProductsTest() {
        log("Starting checkPopularProductsTest...");
        navigateToPage(BASE_URL);

        List<PopularProductCard> popularProducts = homePage.getPopularProducts();

        log("Checking items in 'Popular Products' section...");
        Verify.verifyEquals(popularProducts, getPopularProducts(),
                "Expected items in 'Popular Products' section are present: " + getPopularProducts(),
                "Not expected items are listed in 'Popular Products' section: " + popularProducts);
    }

    @Test(priority = 4, description = "Given the user is on the homepage," +
            " when they check the 'Special Offer' banner," +
            " then the content of the banner should match the expected one")
    public void checkSpecialOfferBanner() {
        log("Starting checkSpecialOfferBanner...");
        navigateToPage(BASE_URL);

        String specialOfferBannerUrl = homePage.getSpecialOfferBannerUrl();

        log("Checking 'Special offer' banner...");
        Verify.verifyEquals(specialOfferBannerUrl, homePageExpectedData.getSpecialOfferBannerUrl(),
                "Expected 'Special Offer' banner is present, banner URL: " + homePageExpectedData.getSpecialOfferBannerUrl(),
                "Not expected ʼSpecial Offer' banner URL: " + specialOfferBannerUrl);
    }

    @Test(priority = 5, description = "Given a user on a homepage," +
            "When they check 'Custom Text' block," +
            "Then the 'Custom Text' block should have the right text in it and correspond the expected one.")
    public void checkCustomTextBlockContent() {
        log("Starting checkCustomTextBlockContent...");
        navigateToPage(BASE_URL);

        String customTextBlockContent = homePage.getCustomTextBlockContent();

        log("Checking 'Custom Text' section content");
        Verify.verifyEquals(customTextBlockContent, homePageExpectedData.getCustomTextBlockContent(),
                "'Custom Text' section has expected content: " + homePageExpectedData.getCustomTextBlockContent(),
                "Not expected ʼCustom Text' section  content found: " + customTextBlockContent);
    }

    @Test(priority = 6, description = "Given a user is on the homepage," +
            " when they interact with the 'Email subscription' section," +
            " then all functions should work as expected")
    public void checkEmailSubscriptionToNewsLetterBlockTest() {
        log("Starting checkEmailSubscriptionToNewsLetterBlockTest...");
        navigateToPage(BASE_URL);

        log("Checking 'Email subscription to newsletter' section label...");
        String emailSubscriptionToNewsLetterBlockLabel = homePage.getEmailSubscriptionToNewsLetterBlockLabel();
        Verify.verifyEquals(emailSubscriptionToNewsLetterBlockLabel,
                homePageExpectedData.getEmailSubscriptionToNewsLetterBlockLabel(),
                "'Email subscription to newsletter' section has expected label: " +
                        homePageExpectedData.getEmailSubscriptionToNewsLetterBlockLabel(),
                "Not expected 'Email subscription to newsletter' section label: " +
                        emailSubscriptionToNewsLetterBlockLabel);

        log("Checking 'Email subscription to newsletter' section tip message...");
        String emailSubscriptionToNewsLetterBlockTipMessage = homePage.getEmailSubscriptionToNewsLetterTip();
        Verify.verifyEquals(emailSubscriptionToNewsLetterBlockTipMessage, homePageExpectedData.getNewsLetterEmailSubscriptionTip(),
                "'Email subscription to newsletter' section has expected tip message: " +
                        homePageExpectedData.getNewsLetterEmailSubscriptionTip(),
                "Not expected 'Email subscription to newsletter' section tim message: " +
                        emailSubscriptionToNewsLetterBlockTipMessage);

        String emailForInput = homePageExpectedData.getEmailForNewsSubscription();
        homePage.inputSubscriptionToNewsLetterEmail(emailForInput);
        homePage.clickEmailSubscriptionToNewsLetterSubscribeButton();

        log("Checking 'Email subscription to newsletter' success message...");
        String emailSubscriptionToNewsLetterBlockSuccessMessage = homePage.getSuccessfulEmailSubscriptionToNewsLetterMessage();
        Verify.verifyEquals(emailSubscriptionToNewsLetterBlockSuccessMessage,
                homePageExpectedData.getSuccessfulSubscriptionToNewsLetterMessage(),
                "'Email subscription to newsletter' section has expected success message: " +
                        homePageExpectedData.getSuccessfulSubscriptionToNewsLetterMessage(),
                "Not expected 'Email subscription to newsletter' section success message: " +
                        emailSubscriptionToNewsLetterBlockSuccessMessage);

        homePage.inputSubscriptionToNewsLetterEmail(emailForInput);
        homePage.clickEmailSubscriptionToNewsLetterSubscribeButton();

        log("Checking 'Email subscription to newsletter' validation error message...");
        String emailSubscriptionToNewsLetterBlockValidationErrorMessage = homePage.getEmailSubscriptionToNewsLetterValidationErrorMessage();
        Verify.verifyEquals(emailSubscriptionToNewsLetterBlockValidationErrorMessage,
                homePageExpectedData.getSubscriptionToNewsLetterValidationErrorMessage(),
                "'Email subscription to newsletter' section has expected validation error message: " +
                        homePageExpectedData.getSubscriptionToNewsLetterValidationErrorMessage(),
                "Not expected 'Email subscription to newsletter' section validation error message: " +
                        emailSubscriptionToNewsLetterBlockValidationErrorMessage);
    }

    @Test(priority = 7, description = "Given a user on the homepage," +
            " when they check the footer block content," +
            " then footer should has all expected columns and items in it")
    public void checkFooterBlockTest() {
        log("Starting checkFooterBlockTest...");
        navigateToPage(BASE_URL);

        log("Checking 'Footer' block content...");
        List<FooterColumn> footerContent = homePage.getFooterBlockContent();
        Verify.verifyEquals(footerContent, homePageExpectedData.getFooterBlockContent(),
                "Footer has expected items: " + homePageExpectedData.getFooterBlockContent(),
                "Not expected footer items found: " + footerContent);
    }
}
