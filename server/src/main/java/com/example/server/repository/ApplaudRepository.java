package com.example.server.repository;

import com.example.server.model.Applaud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ApplaudRepository {

    private final JPAApplaudRepository applaudRepository;

    @Autowired
    public ApplaudRepository(JPAApplaudRepository applaudRepository) {
        this.applaudRepository = applaudRepository;
    }

    public List<Applaud> getApplauds() {
        return Streamable.of(applaudRepository.findAll()).toList();
    }

    public Applaud addApplaud(Applaud applaud) {
       return applaudRepository.save(applaud);
    }
}
