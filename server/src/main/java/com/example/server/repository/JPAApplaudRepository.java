package com.example.server.repository;

import com.example.server.model.Applaud;
import org.springframework.data.repository.CrudRepository;

public interface JPAApplaudRepository extends CrudRepository<Applaud, String> {
}
