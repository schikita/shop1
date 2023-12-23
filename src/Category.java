import java.util.*;
import java.io.*;
class Category implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private List<Product> products;

    public Category(String name) {
        this.name = name;
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }
}