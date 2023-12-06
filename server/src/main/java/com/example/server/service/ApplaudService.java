package com.example.server.service;

import com.example.server.model.Applaud;
import com.example.server.model.Member;
import com.example.server.model.dto.ApplaudDTO;
import com.example.server.model.dto.MemberDTO;
import com.example.server.repository.ApplaudRepository;
import com.example.server.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ApplaudService {

    private final ApplaudRepository applaudRepository;
    private final MemberRepository memberRepository;
    private final ApplaudMapper applaudMapper;

    @Autowired
    public ApplaudService(ApplaudRepository applaudRepository, MemberRepository memberRepository, ApplaudMapper applaudMapper){
        this.applaudRepository = applaudRepository;
        this.memberRepository = memberRepository;
        this.applaudMapper = applaudMapper;
    }

    public List<ApplaudDTO> getAllApplauds() {
        List<Applaud> applauds = applaudRepository.getApplauds();
        return applauds.stream().map(applaudMapper::applaudToDTO).collect(Collectors.toList());
    }

    public List<Applaud> getApplaudsByReceiverId(UUID receiverId) {
        return applaudRepository.findByReceiverId(receiverId);
    }

    public List<Applaud> getPublishedApplaudsByMemberEmail(String memberEmail) {
            return applaudRepository.findByReceiverAndIsPublished(
                    memberRepository.findByEmail(memberEmail),
                    true);
    }

    public String getNumberOfUnreadApplaudsByMemberEmail(String memberEmail) {
            return applaudRepository.getNumberOfUnreadApplaudsByReceiverEmail(
                    memberRepository.findByEmail(memberEmail),
                    false);
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
