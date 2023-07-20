package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    public String showUsers(ModelMap model, HttpServletRequest request) {

        model.addAttribute("resultUsersList", userService.getUsersList());

        return "users";
    }

    @GetMapping("/addUser")
    public String addUsers(@RequestParam("name") String name, @RequestParam("lastName") String lastName, @RequestParam("age") int age,  ModelMap model) {

        List<String> messages = new ArrayList<>();

        User user1 = new User(name, lastName, age);

        userService.addUser(user1);
        messages.add("New user has been added");
        model.addAttribute("messages", messages);
        model.addAttribute("resultUsersList", userService.getUsersList());

        return "users";
    }
    @GetMapping(value = "/deleteUser")
    public String showDeletedUser(@RequestParam("id") int id, ModelMap model) {

        List<String> messages = new ArrayList<>();

        try {
            userService.deleteUserById(id);
            messages.add("User has been deleted");
            model.addAttribute("messages", messages);
        } catch (Exception e) {
            messages.add("Something wrong");
            model.addAttribute("messages", messages);
        }

        model.addAttribute("resultUsersList", userService.getUsersList());
        return "users";
    }

    @GetMapping(value = "/updateUser")
    public String updateUser(@RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("lastName") String lastName, @RequestParam("age") int age, ModelMap model) {

        List<String> messages = new ArrayList<>();

        User newUser = new User(name, lastName, age);
        boolean updateResult = userService.updateUser(id, newUser);
        if (updateResult == true) {
            messages.add("User has been updated");
            model.addAttribute("messages", messages);
        } else {
            messages.add("User with ID: " + id + " doesn't exist");
            model.addAttribute("messages", messages);
        }

        model.addAttribute("resultUsersList", userService.getUsersList());
        return "users";
    }

    @GetMapping(value = "/showUser")
    public String showUserById(@RequestParam("id") int id, ModelMap model) {

        List<String> messages = new ArrayList<>();

        User user = userService.getUserById(id);

        if (user != null) {
            messages.add("Requested user");
            model.addAttribute("resultUsersList", user);
            return "users";
        } else {
            messages.add("Requested user doesn't exist");
            model.addAttribute("messages", messages);
            model.addAttribute("resultUsersList", userService.getUsersList());
            return "users";
        }
    }
}
