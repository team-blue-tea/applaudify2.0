package com.example.server.controller.v1;

import com.example.server.model.Applaud;
import com.example.server.repository.ApplaudRepository;
import com.example.server.service.ApplaudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/applauds")
@CrossOrigin("*")
public class ApplaudControllerV1 {

    private final ApplaudRepository applaudRepository;
    private final ApplaudService applaudService;

    @Autowired
    public ApplaudControllerV1(ApplaudRepository applaudRepository, ApplaudService applaudService) {
        this.applaudRepository=applaudRepository;
        this.applaudService=applaudService;
    }

    @GetMapping
    public List<Applaud> getAllApplauds() {
        return applaudService.getAllApplauds();
    }

    //need to refactor endpoint to be /receiver/{id}
    @GetMapping("/{receiverId}")
    public List<Applaud> getApplaudsByReceiverId(@PathVariable("receiverId") UUID receiverId) {
        return applaudService.getApplaudsByReceiverId(receiverId);
    }

    @GetMapping("/published/{memberEmail}")
    public List<Applaud> getPublishedApplaudsByMemberEmail(@PathVariable("memberEmail") String memberEmail) {
        return applaudService.getPublishedApplaudsByMemberEmail(memberEmail);
    }

    @GetMapping("/unread/{memberEmail}")
    public String getNumberOfUnreadApplaudsByMemberEmail(@PathVariable("memberEmail") String memberEmail) {
        return applaudService.getNumberOfUnreadApplaudsByMemberEmail(memberEmail);
    }

    @PostMapping
    public ResponseEntity<String> createApplaud(@RequestBody Applaud applaud) {
        try {
            applaudRepository.addApplaud(applaud);
            return ResponseEntity.ok("Applaud with id: " + applaud.getId() + " created!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/unread/{applaudId}")
    public String updateApplaudRead(@PathVariable("applaudId") String applaudId, @RequestBody Applaud updatedApplaud) {

        UUID applaudUUID = UUID.fromString(applaudId);
        Applaud existingApplaud = applaudRepository.getApplaudById(applaudUUID);

        existingApplaud.setRead(updatedApplaud.isRead());

        applaudRepository.updateApplaud(existingApplaud);

        return "Applaud with id: " + applaudId + " successfully updated!";
    }

    @PutMapping("/published/{applaudId}")
    public String updateApplaudPublished(@PathVariable("applaudId") String applaudId, @RequestBody Applaud updatedApplaud) {

        UUID applaudUUID = UUID.fromString(applaudId);
        Applaud existingApplaud = applaudRepository.getApplaudById(applaudUUID);

        existingApplaud.setPublished(updatedApplaud.isPublished());

        applaudRepository.updateApplaud(existingApplaud);

        return "Applaud with id: " + applaudId + " successfully updated!";
    }
} 
