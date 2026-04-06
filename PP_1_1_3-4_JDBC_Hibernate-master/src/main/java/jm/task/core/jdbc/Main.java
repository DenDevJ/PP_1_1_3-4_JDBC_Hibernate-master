package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        try {
            System.out.println("=== Создание таблицы ===");
            userService.createUsersTable();
            System.out.println("\n=== Добавление пользователей ===");
            userService.saveUser("Иван", "Иванов", (byte) 25);
            userService.saveUser("Петр", "Петров", (byte) 30);
            userService.saveUser("Мария", "Сидорова", (byte) 22);
            userService.saveUser("Анна", "Кузнецова", (byte) 28);
            System.out.println("\n=== Список всех пользователей ===");
            List<User> users = userService.getAllUsers();
            for (User user : users) {
                System.out.println(user);
            }
            System.out.println("\n=== Очистка таблицы ===");
            userService.cleanUsersTable();
            System.out.println("\n=== Удаление таблицы ===");
            userService.dropUsersTable();
            System.out.println("\n=== Все операции выполнены успешно ===");
        } catch (Exception e) {
            System.err.println("Ошибка в работе приложения: " + e.getMessage());
            e.printStackTrace();
        }
    }
}