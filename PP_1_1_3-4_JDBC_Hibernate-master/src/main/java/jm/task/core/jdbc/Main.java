package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        try {
            // 1. Создание таблицы User(ов)
            System.out.println("=== Создание таблицы ===");
            userService.createUsersTable();
            // 2. Добавление 4 User(ов) в таблицу
            System.out.println("\n=== Добавление пользователей ===");
            userService.saveUser("Иван", "Иванов", (byte) 25);
            userService.saveUser("Петр", "Петров", (byte) 30);
            userService.saveUser("Мария", "Сидорова", (byte) 22);
            userService.saveUser("Анна", "Кузнецова", (byte) 28);
            // 3. Получение всех User из базы и вывод в консоль
            System.out.println("\n=== Список всех пользователей ===");
            List<User> users = userService.getAllUsers();
            for (User user : users) {
                System.out.println(user);
            }
            // 4. Очистка таблицы User(ов)
            System.out.println("\n=== Очистка таблицы ===");
            userService.cleanUsersTable();
            // 5. Удаление таблицы
            System.out.println("\n=== Удаление таблицы ===");
            userService.dropUsersTable();
            System.out.println("\n=== Все операции выполнены успешно ===");
        } catch (Exception e) {
            System.err.println("Ошибка в работе приложения: " + e.getMessage());
            e.printStackTrace();
        }
    }
}