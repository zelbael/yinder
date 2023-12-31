package yinder.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yinder.demo.model.User;
import yinder.demo.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{user_id}")
    public Optional<User> getUserById(@PathVariable("user_id") long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{user_id}")
    public User updateUser(@PathVariable("user_id") long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{user_id}")
    public void deleteUser(@PathVariable("user_id") long id) {
        userService.deleteUser(id);
    }
}
