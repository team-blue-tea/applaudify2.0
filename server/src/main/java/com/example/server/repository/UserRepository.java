package com.example.server.repository;

import com.example.server.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private final JPAUserRepository userRepository;

    @Autowired
    public UserRepository(JPAUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return Streamable.of(userRepository.findAll()).toList();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

}
