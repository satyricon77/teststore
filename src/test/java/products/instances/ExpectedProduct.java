package products.instances;

import products.instances.Product;

import java.util.ArrayList;
import java.util.List;

public class ExpectedProduct {
    public Product getProductDetails() {
        return new Product()
                .setProductName("HUMMINGBIRD PRINTED T-SHIRT")
                .setProductPrice("$19.12")
                .setProductOptions(new ArrayList<>() {{
                    addAll(getDefaultProductSizes());
                    addAll(getDefaultProductColors());
                    add(getDefaultProductQuantity());
                }});
    }

    //can be overwritten in custom product class
    private List<String> getDefaultProductSizes() {
        return List.of("S", "M", "L", "XL");
    }

    //can be overwritten in custom product class
    private List<String> getDefaultProductColors() {
        return List.of("White", "Black");
    }

    //can be overwritten in custom product class
    private String getDefaultProductQuantity() {
        return "1";
    }

    public Product getCustomizedProduct(){
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
