package com.example.server.service;

import com.example.server.model.Applaud;
import com.example.server.repository.ApplaudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ApplaudService {

    private final ApplaudRepository applaudRepository;

    @Autowired
    public ApplaudService(ApplaudRepository applaudRepository){
        this.applaudRepository = applaudRepository;
    }

    public List<Applaud> getAllApplauds() {
        return applaudRepository.getApplauds();
    }

    public List<Applaud> getApplaudsByReceiverId(UUID receiverId) {
        var applauds = applaudRepository.findByReceiverId(receiverId);
        if (applauds == null) {
            return new ArrayList<>();
        }
        return applauds;
    }
}
