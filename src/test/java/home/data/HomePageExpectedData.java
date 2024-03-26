package home.data;

import home.instances.BannerCard;
import home.instances.FooterColumn;
import home.instances.PopularProductCard;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class HomePageExpectedData {
    public List<List<String>> getHeaderMenuTabContents() {
        return List.of(
                List.of("CLOTHES", "MEN", "WOMEN"),
                List.of("ACCESSORIES", "STATIONERY", "HOME ACCESSORIES"),
                List.of("ART")
        );
    }

    public String getSpecialOfferBannerUrl() {
        return "https://teststore.automationtesting.co.uk/modules/ps_banner/img/sale70.png";
    }

    public String getCustomTextBlockContent() {
        return "CUSTOM TEXT BLOCK\n" +
                "Lorem ipsum dolor sit amet conse ctetu\n" +
                "Sit amet conse ctetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                " Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit.";
    }

    public String getEmailForNewsSubscription() {
        String formatPattern = new SimpleDateFormat("HHmmss").format(new Date());
        return "someemailnamejustfortest" + formatPattern + "@gmail.com";
    }

    public String getEmailSubscriptionToNewsLetterBlockLabel() {
        return "Get our latest news and special sales";
    }

    public String getSuccessfulSubscriptionToNewsLetterMessage() {
        return "You have successfully subscribed to this newsletter.";
    }

    public String getSubscriptionToNewsLetterValidationErrorMessage() {
        return "This email address is already registered.";
    }

    public String getNewsLetterEmailSubscriptionTip() {
        return "You may unsubscribe at any moment. For that purpose, please find our contact info in the legal notice.";
    }

    public List<FooterColumn> getFooterBlockContent() {
        return List.of(new FooterColumn("PRODUCTS", List.of("Prices drop", "New products", "Best sellers")),
                new FooterColumn("OUR COMPANY", List.of("Delivery", "Legal Notice", "Terms and conditions of use",
                        "About us", "Secure payment", "Contact us", "Sitemap", "Stores")),
                new FooterColumn("YOUR ACCOUNT", List.of("Order tracking", "Sign in", "Create account",
                        "My alerts")),
                new FooterColumn("STORE INFORMATION", List.of("Test Store\n" + "United States\n" +
                        "Email us: test@test.com")));
    }

    public enum Banner {
        SAMPLE_ONE("SAMPLE 1", "EXCEPTEUR OCCAECAT", "Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
                " Proin tristique in tortor et dignissim. Quisque non tempor leo. Maecenas egestas sem elit"),
        SAMPLE_TWO("SAMPLE 2", "EXCEPTEUR OCCAECAT", "Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
                " Proin tristique in tortor et dignissim. Quisque non tempor leo. Maecenas egestas sem elit"),
        SAMPLE_THREE("SAMPLE 3", "EXCEPTEUR OCCAECAT", "Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
                " Proin tristique in tortor et dignissim. Quisque non tempor leo. Maecenas egestas sem elit");

        private final BannerCard BANNER_CARD;

        Banner(String bannerHeader, String bannerSubHeader, String bannerDescription) {
            this.BANNER_CARD = new BannerCard(bannerHeader, bannerSubHeader, bannerDescription);
        }

        public static List<BannerCard> getBannersContent() {
            return Arrays.asList(values()).stream()
                    .map(banner -> banner.BANNER_CARD)
                    .collect(Collectors.toList());
        }
    }

    public enum PopularProduct {
        PRINTED_T_SHIRT("Hummingbird Printed T-Shirt", "$19.12", "$23.90", "-20%"),
        PRINTED_SWEATER("Hummingbird Printed Sweater", "$28.72", "$35.90", "-20%"),
        BEST_TO_COME_FRAMED_POSTER("The Best Is Yet To Come'...", "$29.00"),
        ADVENTURE_BEGINS_FRAMED_POSTER("The Adventure Begins Framed...", "$29.00"),
        TODAY_IS_A_GOOD_DAY_FRAMED_POSTER("Today Is A Good Day Framed...", "$29.00"),
        THE_BEST_IS_YET_TO_COME_MUG("Mug The Best Is Yet To Come", "$11.90"),
        THE_ADVENTURE_BEGINS_MUG("Mug The Adventure Begins", "$11.90"),
        TODAY_IS_A_GOOD_DAY_MUG("Mug Today Is A Good Day", "$11.90");

        private final PopularProductCard POPULAR_PRODUCT_CARD;

        PopularProduct(String productTitle, String productCurrentPrice, String productOldPrice, String productDiscountPercent) {
            this.POPULAR_PRODUCT_CARD = new PopularProductCard(productTitle, productCurrentPrice, productOldPrice, productDiscountPercent);
        }

        PopularProduct(String productTitle, String productCurrentPrice) {
            this.POPULAR_PRODUCT_CARD = new PopularProductCard(productTitle, productCurrentPrice);
        }

        public static List<PopularProductCard> getPopularProducts() {
            return Arrays.asList(values()).stream()
                    .map(productCard -> productCard.POPULAR_PRODUCT_CARD)
                    .collect(Collectors.toList());
        }
    }
}
