package org.volovich.model.products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Product {
    protected String productName;
    protected String productPrice;
    protected List<String> productOptions;

    public Product(String productName, String productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productOptions = new ArrayList<>();
    }
}
