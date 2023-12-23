import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        // инициализация каталога и других компонентов системы
        Catalog catalog = new Catalog();
        Category category1 = new Category("Electronics");
        category1.addProduct(new Product("Laptop", 799.99, 4));
        category1.addProduct(new Product("Smartphone", 299.99, 5));
        Category category2 = new Category("Clothing");
        category2.addProduct(new Product("T-Shirt", 19.99, 4));
        category2.addProduct(new Product("Jeans", 39.99, 3));
        catalog.addCategory(category1);
        catalog.addCategory(category2);

        AuthenticationManager authManager = new AuthenticationManager();
        RegistrationManager regManager = new RegistrationManager();
        DataStorage dataStorage = new DataStorage();
        LocalizationManager localizationManager = new LocalizationManager(Locale.getDefault());

        try {
            // загрузка данных из файлов (если есть)
            catalog = (Catalog) dataStorage.loadData("catalog.ser");
            authManager = (AuthenticationManager) dataStorage.loadData("authManager.ser");
            regManager = (RegistrationManager) dataStorage.loadData("regManager.ser");
        } catch (IOException | ClassNotFoundException e) {
            // обработка ошибок при загрузке данных
        }

        Scanner scanner = new Scanner(System.in);
        User currentUser = null;
        AuthenticationManager authenticationManager = new AuthenticationManager();
        CommandManager commandManager = new CommandManager(catalog, authenticationManager);

        while (true) {
            System.out.println("Enter command: ");
            String command = scanner.nextLine();

            if (command.equals("exit")) {
                try {
                    // сохранение данных перед выходом
                    dataStorage.saveData(catalog, "catalog.ser");
                    dataStorage.saveData(authManager, "authManager.ser");
                    dataStorage.saveData(regManager, "regManager.ser");
                } catch (IOException e) {
                    // обработка ошибок при сохранении данных
                }
                break;
            }

            commandManager.processCommand(command, commandManager);
        }

        scanner.close();
    }
}