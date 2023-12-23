import java.util.*;
import java.io.*;
class Catalog implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Category> categories;

    public Catalog() {
        this.categories = new ArrayList<>();
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public List<Category> getCategories() {
        return categories;
    }
}
