package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {

    boolean updateUser(int oldUsersId, User newUser);
    User getUserById(int id);
    List<User> getUsersList();
    void addUser(User user);
    void deleteUserById(int id);
}
