package home.instances;

import java.util.Objects;

public class PopularProductCard {
    private String productTitle;
    private String productOldPrice;
    private String productCurrentPrice;
    private String productDiscountPercent;

    public PopularProductCard(String productTitle, String productCurrentPrice) {
        this.productTitle = productTitle;
        this.productCurrentPrice = productCurrentPrice;
    }

    public PopularProductCard(String productTitle, String productCurrentPrice, String productOldPrice, String productDiscountPercent) {
        this.productTitle = productTitle;
        this.productCurrentPrice = productCurrentPrice;
        this.productOldPrice = productOldPrice;
        this.productDiscountPercent = productDiscountPercent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PopularProductCard that = (PopularProductCard) o;
        return Objects.equals(productTitle, that.productTitle) && Objects.equals(productOldPrice, that.productOldPrice) &&
                Objects.equals(productCurrentPrice, that.productCurrentPrice) && Objects.equals(productDiscountPercent, that.productDiscountPercent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productTitle, productOldPrice, productCurrentPrice, productDiscountPercent);
    }

    @Override
    public String toString() {
        return "Product card title: " + productTitle + "\n" +
                "Product card old price: " + productOldPrice + "\n" +
                "Product card current price: " + productCurrentPrice + "\n" +
                "Product card discount percent: " + productDiscountPercent + "\n";
    }
}
