package com.example.server.controller.v1;

import com.example.server.model.Applaud;
import com.example.server.repository.ApplaudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applauds")
public class ApplaudControllerV1 {

    private final ApplaudRepository applaudRepository;

    @Autowired
    public ApplaudControllerV1(ApplaudRepository applaudRepository) {
        this.applaudRepository=applaudRepository;
    }

    @GetMapping
    public List<Applaud> getAllApplauds() {
        return applaudRepository.getApplauds();
    }

    @PostMapping
    public Applaud createApplaud(@RequestBody Applaud applaud) {
        return applaudRepository.addApplaud(applaud);
    }
}
