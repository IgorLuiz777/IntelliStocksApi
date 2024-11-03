package br.com.intellistocks.api.controller;

import java.util.List;
import java.util.Optional;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.intellistocks.api.models.user.User;
import br.com.intellistocks.api.service.UserService;

@RestController
@RequestMapping("user")
@Tag(name = "User", description = "Endpoints relacionados ao Usuário")
public class UserController {
    
    @Autowired
    public UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.userList();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping
    public User postUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public User putUser(@RequestBody User user, @PathVariable Long id)
    {
        return userService.editUser(user, id);
    }

    @DeleteMapping("/{ìd}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
