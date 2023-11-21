package com.example.server.service;

import com.example.server.model.Applaud;
import com.example.server.model.Member;
import com.example.server.repository.ApplaudRepository;
import com.example.server.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ApplaudService {

    private final ApplaudRepository applaudRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public ApplaudService(ApplaudRepository applaudRepository, MemberRepository memberRepository){
        this.applaudRepository = applaudRepository;
        this.memberRepository = memberRepository;
    }

    public List<Applaud> getAllApplauds() {
        return applaudRepository.getApplauds();
    }

    public List<Applaud> getApplaudsByReceiverId(UUID receiverId) {
        var applauds = applaudRepository.findByReceiverId(receiverId);
        if (applauds == null) {
            return Collections.emptyList();
        }
        return applauds;
    }

    public List<Applaud> getPublishedApplaudsByMemberEmail(String memberEmail) {
        Member member = memberRepository.findByEmail(memberEmail);
        if (member != null) {
            return applaudRepository.findByReceiverAndIsPublished(member, true);
        }
        return Collections.emptyList();
    }

    public String getNumberOfUnreadApplaudsByMemberEmail(String memberEmail) {
        Member member = memberRepository.findByEmail(memberEmail);
        if (member != null) {
            return applaudRepository.getNumberOfUnreadApplaudsByReceiverEmail(member, false);
        }
        return "0";
    }

    public Applaud addApplaud(Applaud applaud) throws IllegalArgumentException {
        validateApplaudData(applaud);
        return applaudRepository.addApplaud(applaud);
    }

    public void updateApplaud(UUID applaudId, Applaud updatedApplaud, String fieldToUpdate) throws IllegalArgumentException {
            var existingApplaud = applaudRepository.getApplaudById(applaudId)
                .orElseThrow(() -> new IllegalArgumentException("Applaud with id: "+ applaudId +" does not exist"));
            switch (fieldToUpdate) {
                case "read" -> existingApplaud.setRead(updatedApplaud.isRead());
                case "published" -> existingApplaud.setPublished(updatedApplaud.isPublished());
                default -> throw new IllegalArgumentException("Invalid field to update");
            }
            applaudRepository.updateApplaud(existingApplaud);
    }

    private void validateApplaudData(Applaud applaud) {
        if (applaud.getSender() == null) {
            throw new IllegalArgumentException("Sender cannot be null");
        }
        if (applaud.getReceiver() == null) {
            throw new IllegalArgumentException("Receiver cannot be null");
        }
        if (applaud.getComment() == null) {
            throw new IllegalArgumentException("Comment cannot be null");
        }
        memberRepository.findById(applaud.getSender().getId())
                .orElseThrow(() -> new IllegalArgumentException("Sender with id: "+ applaud.getSender().getId() +" does not exist"));
        memberRepository.findById(applaud.getReceiver().getId())
                .orElseThrow(() -> new IllegalArgumentException("Receiver with id: "+ applaud.getReceiver().getId() +" does not exist"));
    }
}
