package products.data;

import org.testng.annotations.DataProvider;

import java.util.Arrays;

public class ProductsDataProvider {
    @DataProvider
    public Object[][] sortingTypes() {
        return Arrays.stream(ProductsExpectedData.SortingType.values())
                .map(sortingType -> new Object[]{sortingType})
                .toArray(Object[][]::new);
    }
}
