package com.example.server.controller.v1;

import com.example.server.model.Applaud;
import com.example.server.repository.ApplaudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/applauds")
@CrossOrigin("*")
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
    public String createApplaud(@RequestBody Applaud applaud) {
        applaudRepository.addApplaud(applaud);
        return "Applaud with id: " + applaud.getId() + " created!";
    }
    @GetMapping("/{receiverId}")
    public List<Applaud> getApplaudsByReceiver(@PathVariable("receiverId") String receiverId) {
        return applaudRepository.getApplaudsByReceiver(receiverId);
    }

    @GetMapping("/published/{memberEmail}")
    public List<Applaud> getPublishedApplaudsByMemberEmail(@PathVariable("memberEmail") String memberEmail) {
        return applaudRepository.getPublishedApplaudsByMemberEmail(memberEmail);
    }

    @GetMapping("/unread/{memberEmail}")
    public String getNumberOfUnreadApplaudsByMemberEmail(@PathVariable("memberEmail") String memberEmail) {
        return applaudRepository.getNumberOfUnreadApplaudsByMemberEmail(memberEmail);
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
