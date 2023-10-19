package com.example.server.repository;

import com.example.server.model.User;
import org.springframework.data.repository.CrudRepository;

public interface JPAUserRepository extends CrudRepository<User, String> {
}
