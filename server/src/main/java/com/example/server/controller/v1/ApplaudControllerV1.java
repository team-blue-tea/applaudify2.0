package com.example.server.controller.v1;

import com.example.server.repository.ApplaudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/applauds")
public class ApplaudControllerV1 {

    private final ApplaudRepository applaudRepository;

    @Autowired
    public ApplaudControllerV1(ApplaudRepository applaudRepository) {
        this.applaudRepository=applaudRepository;
    }

}
