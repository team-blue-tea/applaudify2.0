package com.example.server.controller.v1;

import com.example.server.model.Applaud;
import com.example.server.service.ApplaudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/applauds")
@CrossOrigin("*")
public class ApplaudControllerV1 {

    private final ApplaudService applaudService;

    @Autowired
    public ApplaudControllerV1(ApplaudService applaudService) {
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
    public ResponseEntity<?> createApplaud(@RequestBody Applaud applaud) {
        try {
            var newApplaud = applaudService.addApplaud(applaud);
            return ResponseEntity.created(URI.create("api/v1/applauds/" + newApplaud.getId())).body(newApplaud);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    //need to refactor frontend to send to be /update/{applaudId}
    @PutMapping("/update/{applaudId}")
    public ResponseEntity<?> updateApplaud(
            @PathVariable("applaudId") UUID applaudId,
            @RequestBody Applaud updatedApplaud,
            @RequestParam("field") String fieldToUpdate) {
        try {
            applaudService.updateApplaud(applaudId, updatedApplaud, fieldToUpdate);
            return ResponseEntity.ok("Applaud with id: " + applaudId + " successfully updated!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
} 
