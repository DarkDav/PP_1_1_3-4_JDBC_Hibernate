package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao any = new UserDaoJDBCImpl();

    public void createUsersTable() {
        any.createUsersTable();
    }

    public void dropUsersTable() {
        any.dropUsersTable();

    }

    public void saveUser(String name, String lastName, byte age) {

        any.saveUser(name, lastName, age);
        System.out.println("User с именем – " + name + " есть контакт)");
    }

    public void removeUserById(long id) {
        any.removeUserById(id);
    }

    public List<User> getAllUsers() {
        List<User> users = any.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
        return users;
    }


    public void cleanUsersTable() {
        any.cleanUsersTable();
    }
}
