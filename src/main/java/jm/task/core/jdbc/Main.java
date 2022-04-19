package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;


public class Main {
    private static final UserService userService = new UserServiceImpl();
    private static final User user1 = new User("Odin", "Odinson", (byte) 30);
    private static final User user2 = new User("Vasya", "Ivanov", (byte) 18);
    private static final User user3 = new User("Joni", "Depp", (byte) 46);
    private static final User user4 = new User("Harry", "Ozborn", (byte) 52);

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();


        userService.createUsersTable();
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        for (User a : userList
        ) {
            userService.saveUser(a.getName(), a.getLastName(), a.getAge());
            System.out.println(a);

        }

        userService.removeUserById(1);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
        // реализуйте алгоритм здесь
    }
}
