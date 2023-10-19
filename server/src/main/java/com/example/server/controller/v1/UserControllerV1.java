package com.example.server.controller.v1;

import com.example.server.model.User;
import com.example.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@CrossOrigin("*")
public class UserControllerV1 {

    private final UserRepository repo;

    @Autowired
    public UserControllerV1(UserRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<User> getUsers() {
        return repo.getUsers();
    }

    @PostMapping
    public String addUser(@RequestBody User user) {
        repo.addUser(user);
        return user.getName() + " successfully added to DB";
    }
}
