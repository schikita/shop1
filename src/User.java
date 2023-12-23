import java.util.*;
import java.io.*;
class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private Basket basket;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.basket = new Basket();
    }

    public Basket getBasket() {
        return basket;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
