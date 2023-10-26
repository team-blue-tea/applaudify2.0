package com.example.server.repository;

import com.example.server.model.Applaud;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface JPAApplaudRepository extends CrudRepository<Applaud, String> {
    List<Applaud> findByReceiverId(UUID receiverId);
}
