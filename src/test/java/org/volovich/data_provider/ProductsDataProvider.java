package org.volovich.data_provider;

import org.testng.annotations.DataProvider;
import org.volovich.utils.products.ProductsExpectedData;

import java.util.Arrays;

public class ProductsDataProvider {
    @DataProvider
    public Object[][] sortingTypes() {
        return Arrays.stream(ProductsExpectedData.SortingType.values())
                .map(sortingType -> new Object[]{sortingType})
                .toArray(Object[][]::new);
    }
}
