package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.*;

import java.util.*;

public interface UserService {
    void createUsersTable();

    void dropUsersTable();

    void saveUser(String name, String lastName, byte age);

    void removeUserById(long id);

    List<User> getAllUsers();

    void cleanUsersTable();
}
