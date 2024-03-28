package org.volovich.utils.products;

import org.volovich.model.products.Product;

import java.util.ArrayList;
import java.util.List;

public class ExpectedProduct {
    public static final List<String> DEFAULT_PRODUCT_SIZES = List.of("S", "M", "L", "XL");
    public static final List<String> DEFAULT_PRODUCT_COLORS = List.of("White", "Black");
    public static final String  DEFAULT_PRODUCT_QUANTITY = "1";

    public static Product getProductDetails() {
        return new Product()
                .setProductName("HUMMINGBIRD PRINTED T-SHIRT")
                .setProductPrice("$19.12")
                .setProductOptions(new ArrayList<>() {{
                    addAll(DEFAULT_PRODUCT_COLORS);
                    addAll(DEFAULT_PRODUCT_SIZES);
                    add(DEFAULT_PRODUCT_QUANTITY);
                }});
    }

    public static Product getCustomizedProduct() {
        return new Product()
                .setProductName("Hummingbird printed t-shirt")
                .setProductPrice("$19.12")
                .setProductOptions(new ArrayList<>() {{
                    add("S");
                    add("White");
                    add("1");
                }});
    }

}
