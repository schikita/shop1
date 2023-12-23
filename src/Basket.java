import java.util.*;
import java.io.*;
class Basket implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Product> products;
    private Date purchaseDate;

    public Basket() {
        this.products = new ArrayList<>();
        this.purchaseDate = new Date();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }
}