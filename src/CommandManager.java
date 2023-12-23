import java.util.*;
import java.io.*;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.in;

public class CommandManager {
    private Catalog catalog;
    private User currentUser;
    private AuthenticationManager authenticationManager;

    public CommandManager(Catalog catalog, AuthenticationManager authenticationManager) {
        this.catalog = catalog;
        this.currentUser = null;
        this.authenticationManager = authenticationManager;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    Scanner scanner = new Scanner(in);

    public void processCommand(String command, CommandManager commandManager) {
        if (currentUser == null) {
            handleUnauthenticatedUserCommands(command, commandManager);
        } else if (currentUser instanceof Admin) {
            handleAdminCommands(command);
        } else if (currentUser instanceof Visitor) {
            handleVisitorCommands(command);
        }
    }
    private void handleUnauthenticatedUserCommands(String command, CommandManager commandManager) {
        switch (command) {
            case "login":
                // Обработка команды для входа в систему
                // Запросить у пользователя логин и пароль с клавиатуры

                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                // Проверить логин и пароль в базе данных пользователей
                User user = authenticationManager.getUserByUsername(username);
                if (user != null && user.getPassword().equals(password)) {
                    commandManager.setCurrentUser(user);
                    System.out.println("Login successful!");
                } else {
                    System.out.println("Invalid username or password. Please try again.");
                }
                break;

            case "register":
                // Обработка команды для регистрации нового пользователя
                // Запросить у пользователя желаемый логин и пароль с клавиатуры
                System.out.print("Enter desired username: ");
                String newUsername = scanner.nextLine();
                System.out.print("Enter password: ");
                String newPassword = scanner.nextLine();

                // Создать нового пользователя и добавить его в базу данных пользователей
                User newUser = new Visitor(newUsername, newPassword);
                authenticationManager.addUser(newUser);
                System.out.println("Registration successful! You can now login.");
                break;
            default:
                // Обработка других команд для незалогиненных пользователей
                System.out.println("Unknown command. Please login or register to proceed.");
                break;
        }
    }


    private void handleAdminCommands(String command) {
        switch (command) {
            case "addProduct":
                // обработка команды для добавления товара в каталог
                break;
            case "removeProduct":
                // обработка команды для удаления товара из каталога
                break;
            case "updateProduct":
                // обработка команды для обновления информации о товаре в каталоге
                break;
            default:
                // обработка других команд для администратора
                break;
        }
    }

    private void handleVisitorCommands(String command) {
        switch (command) {
            case "viewCatalog":
                // обработка команды для просмотра каталога товаров
                List<Category> categories = catalog.getCategories();
                // вывод категорий и товаров на экран
                break;
            case "addToBasket":
                // обработка команды для добавления товара в корзину
                break;
            case "viewBasket":
                // обработка команды для просмотра содержимого корзины
                Basket basket = currentUser.getBasket();
                // вывод товаров в корзине на экран
                break;
            case "checkout":
                // обработка команды для оформления заказа
                // передача корзины на обработку оплаты
                PaymentProcessor paymentProcessor = new PaymentProcessor();
                paymentProcessor.processPayment(currentUser.getBasket());
                // очистка корзины после успешного оформления заказа
                currentUser.getBasket().getProducts().clear();
                break;
            default:
                // обработка других команд для посетителя
                break;
        }
    }
}

