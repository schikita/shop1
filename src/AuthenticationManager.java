import java.util.*;
import java.io.*;
class AuthenticationManager {
    private Map<String, User> users;

    /*public AuthenticationManager() {
        this.users = new HashMap<>();
    }*/

    public AuthenticationManager() {
        this.users = new HashMap<>();
        // Предположим, что у вас есть база данных пользователей, где ключ - это логин пользователя, а значение - объект User
        users.put("admin", new User("admin", "adminpassword"));
        // Добавьте других пользователей при необходимости
    }


    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    public User getUserByUsername(String username) {
        return users.get(username);
    }

    public boolean authenticateUser(String username, String password) {
        User user = users.get(username);
        return user != null && user.getPassword().equals(password);
    }
}