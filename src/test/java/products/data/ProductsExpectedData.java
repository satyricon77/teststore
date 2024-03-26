package products.data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ProductsExpectedData {
    public static final String MUG_PRODUCT_TITLE = "Mug";
    public static final String T_SHIRT_PRODUCT_TITLE = "Hummingbird Printed T-Shirt";

    public enum SortingType {
        A_TO_Z("Name, A to Z"),
        Z_TO_A("Name, Z to A"),
        PRICE_LOW_TO_HIGH("Price, low to high"),
        PRICE_HIGH_TO_LOW("Price, high to low");

        private final String type;

        SortingType(String type) {
            this.type = type;
        }

        public String getSortingType() {
            return type;
        }

        public List<String> getAllSortingTypes(){
            return Arrays
                    .stream(values())
                    .map(type -> getSortingType())
                    .collect(Collectors.toList());
        }
    }
}
