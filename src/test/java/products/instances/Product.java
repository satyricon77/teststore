package products.instances;

import java.util.List;
import java.util.Objects;

public class Product {
    protected String productName;
    protected String productPrice;
    protected List<String> productOptions;

    public Product(String productName, String productPrice, List<String> productOptions) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productOptions = productOptions;
    }

    public Product(String productName, String productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public Product() {}

    public String getProductName() {
        return productName;
    }

    public Product setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public Product setProductPrice(String productPrice) {
        this.productPrice = productPrice;
        return this;
    }

    public List<String> getProductOptions() {
        return productOptions;
    }

    public Product setProductOptions(List<String> productOptions) {
        this.productOptions = productOptions;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productName, product.productName) && Objects.equals(productPrice, product.productPrice) && Objects.equals(productOptions, product.productOptions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, productPrice, productOptions);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Product name: ").append(productName).append('\'');
        stringBuilder.append("Product price: ").append(productPrice).append('\'');

        if (productOptions != null) {
            stringBuilder.append("Product options: ").append(productOptions);
        }

        return stringBuilder.toString();
    }
}
