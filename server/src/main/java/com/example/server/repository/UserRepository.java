package com.example.server.repository;

import com.example.server.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private final JPAUserRepository repo;

    @Autowired
    public UserRepository(JPAUserRepository repo) {
        this.repo = repo;
    }

    public List<User> getUsers() {
        return Streamable.of(repo.findAll()).toList();
    }

    public void addUser(User user) {
        repo.save(user);
    }



}
