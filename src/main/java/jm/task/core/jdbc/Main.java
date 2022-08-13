package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserService userService=new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Иван", "Иванов", (byte) 20);
        userService.saveUser("Сидор", "Сидоров", (byte) 30);
        userService.saveUser("Петр", "Петров", (byte) 40);
        userService.saveUser("Michael", "Jackson", (byte) 50);

        userService.getAllUsers();
        userService.removeUserById(2);
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}