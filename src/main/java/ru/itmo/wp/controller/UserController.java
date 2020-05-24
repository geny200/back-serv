package ru.itmo.wp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.wp.domain.Access;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.security.AUser;
import ru.itmo.wp.security.AnyRole;
import ru.itmo.wp.security.Guest;
import ru.itmo.wp.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/1")
public class UserController extends ApiController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @AUser
    @GetMapping("users/authorized")
    public User findAuthorized(User user) {
        return user;
    }

    @AnyRole(Access.Name.ADMIN)
    @GetMapping("users")
    public List<User> findAll() {
        return userService.findAll();
    }

    @Guest
    @GetMapping("hello")
    public String helloPing() {
        return "Hello";
    }
}
