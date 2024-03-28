package org.volovich.pages.home;

import org.volovich.model.home_page.BannerCard;
import org.volovich.model.home_page.FooterColumn;
import org.volovich.model.home_page.PopularProductCard;
import org.volovich.pages.base.BasePage;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.allMatch;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.step;

public class HomePage extends BasePage {
    private final SelenideElement USER_NAME_HEADER = $x("//div[@class='user-info']//a[contains(@title,'my customer account')]");
    private final ElementsCollection BANNERS = $$x("//li[contains(@class, 'carousel-item')]");
    private final ElementsCollection POPULAR_PRODUCT_CARDS = $$x("//*[@class='featured-products clearfix']//*[contains(@class, 'product-miniature')]");
    private final SelenideElement NEWS_LETTER_BLOCK = $x("//div[contains(@class, 'block_newsletter')]");
    private final SelenideElement FOOTER_BLOCK = $(By.className("footer-container"));
    private final SelenideElement SEARCH_BOX = $x("//div[@id='search_widget']//input[@type='text']");
    private final SelenideElement SHOPPING_CART_ICON = $x("//div[@id='_desktop_cart']");

    public void clickSignInButton() {
        $x("//div[@id='_desktop_user_info']")
                .shouldBe(visible)
                .click();
    }

    public boolean isUserLoggedIn(String expectedUserName) {
        return USER_NAME_HEADER
                .shouldBe(visible)
                .text().equals(expectedUserName);
    }

    public boolean isUserLoggedIn() {
        return USER_NAME_HEADER
                .isDisplayed();
    }

    public void signOutFromLoggedInUser() {
        step("sign out", () -> {
            if (USER_NAME_HEADER.is(visible)) {
                $x("//div[@class='user-info']//*[@class='logout hidden-sm-down']")
                        .shouldBe(visible)
                        .click();
            }
        });
    }

    public List<List<String>> getHeaderMenuTabContents() {
        List<List<String>> tabNames = new ArrayList<>();
        ElementsCollection headerMenuTabs = $$x("//ul[@id='top-menu']//a[@class='dropdown-item']");
        //not good-looking locator because of complex layout
        By mainTabSubTabsXpath = By.xpath(".//following-sibling::div//li[@class='category']");
        for (SelenideElement tab : headerMenuTabs) {
            tab.shouldBe(visible);
            List<String> headerTabContent = new ArrayList<>();
            tab.hover();

            if (!tab.$$(mainTabSubTabsXpath).isEmpty()) {
                headerTabContent.add(tab.text().trim());
                headerTabContent.addAll(tab.$$(mainTabSubTabsXpath).texts());
            } else {
                headerTabContent.add(tab.getText().trim());
            }

            tabNames.add(headerTabContent);
        }

        return tabNames;
    }

    public List<BannerCard> getBannersContent() {
        List<BannerCard> bannerCards = new ArrayList<>();

        By bannerHeaderXpath = By.xpath(".//h2[contains(@class, 'text-uppercase')]");
        By bannerSubHeaderXpath = By.xpath(".//div[@class= 'caption-description']/h3");
        By bannerDescriptionXpath = By.xpath(".//div[@class= 'caption-description']/p");

        BANNERS.shouldHave(size(3));

        for (SelenideElement banner : BANNERS) {
            banner.shouldBe(visible);
            bannerCards.add(new BannerCard(banner.$(bannerHeaderXpath).text(), banner.$(bannerSubHeaderXpath).text(),
                    banner.$(bannerDescriptionXpath).text()));

            $x("//span[@class='icon-next']/i[contains(@class, 'icons')]")
                    .shouldBe(visible)
                    .click();

            banner.shouldNotBe(visible);
        }

        return bannerCards;
    }

    public List<PopularProductCard> getPopularProducts() {
        By productTitleXpath = By.xpath(".//h3[contains(@class, 'product-title')]");
        By productOldPriceXpath = By.xpath(".//div[@class='product-price-and-shipping']//span[@class='regular-price']");
        By productCurrentPriceXpath = By.xpath(".//div[@class='product-price-and-shipping']//span[@class='price']");
        By productDiscountXpath = By.xpath(".//li[@class='product-flag discount']");

        List<PopularProductCard> popularProducts = new ArrayList<>();

        POPULAR_PRODUCT_CARDS.should(allMatch("All popular product cards should be visible", card -> card.isDisplayed()));

        for (SelenideElement productCard : POPULAR_PRODUCT_CARDS) {
            if (productCard.$(productDiscountXpath).is(exist)) {
                popularProducts.add(new PopularProductCard(productCard.$(productTitleXpath).text(),
                        productCard.$(productCurrentPriceXpath).text(), productCard.$(productOldPriceXpath).text(),
                        productCard.$(productDiscountXpath).getOwnText()));
            } else {
                popularProducts.add(new PopularProductCard(productCard.$(productTitleXpath).text(),
                        productCard.$(productCurrentPriceXpath).text()));
            }
        }
        return popularProducts;
    }

    public String getSpecialOfferBannerUrl() {
        return $x("//a[@class='banner']/img")
                .shouldBe(visible)
                .attr("src");
    }

    public String getCustomTextBlockContent() {
        return $x("//div[@id='custom-text']")
                .shouldBe(visible)
                .text();
    }

    public String getEmailSubscriptionToNewsLetterBlockLabel() {
        return NEWS_LETTER_BLOCK.$x(".//p[@id='block-newsletter-label']")
                .shouldBe(visible)
                .text();
    }

    public void inputSubscriptionToNewsLetterEmail(String email) {
        NEWS_LETTER_BLOCK.$x(".//div[@class='input-wrapper']/input[@name='email']")
                .shouldBe(visible)
                .setValue(email);
    }

    public void clickEmailSubscriptionToNewsLetterSubscribeButton() {
        NEWS_LETTER_BLOCK.$x(".//input[@name='submitNewsletter' and contains(@value, 'Subscribe')]")
                .shouldBe(enabled)
                .click();
    }

    public String getSuccessfulEmailSubscriptionToNewsLetterMessage() {
        return NEWS_LETTER_BLOCK.$x(".//p[contains(@class, 'alert-success')]")
                .shouldBe(visible)
                .text();
    }

    public String getEmailSubscriptionToNewsLetterValidationErrorMessage() {
        return NEWS_LETTER_BLOCK.$x(".//p[contains(@class, 'alert-danger')]")
                .shouldBe(visible)
                .text();
    }

    public String getEmailSubscriptionToNewsLetterTip() {
        return NEWS_LETTER_BLOCK.$x(".//div[contains(@class, 'col-xs')]/p")
                .shouldBe(visible)
                .text();
    }

    public List<FooterColumn> getFooterBlockContent() {
        List<FooterColumn> footerColumns = new ArrayList<>();

        for (SelenideElement footerColumnElement : FOOTER_BLOCK.$$x(".//div[contains(@class, 'wrapper')]")) {
            footerColumnElement.shouldBe(visible);
            String columnTitle = footerColumnElement.$x(".//p[contains(@class, 'hidden-sm-down')]").text();
            List<String> columnItems = new ArrayList<>();

            //separate section in footer block
            if (columnTitle.equalsIgnoreCase("Store Information")) {
                columnItems.add(footerColumnElement.$x(".//div[@id='contact-infos']").text());
                footerColumns.add(new FooterColumn(columnTitle, columnItems));
                continue;
            }

            for (SelenideElement footerColumnItem : footerColumnElement.$$x(".//li")) {
                columnItems.add(footerColumnItem.text());
            }

            footerColumns.add(new FooterColumn(columnTitle, columnItems));
        }

        return footerColumns;
    }

    public void inputSearchBox(String value){
        SEARCH_BOX.shouldBe(visible)
                .setValue(value)
                .pressEnter();
    }

    public void clickShoppingCartIcon(){
        SHOPPING_CART_ICON.shouldBe(clickable)
                .click();
    }
}
