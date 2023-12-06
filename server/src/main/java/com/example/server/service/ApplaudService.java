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
        return applaudRepository.getApplauds()
                .stream()
                .map(applaudMapper::applaudToDTO)
                .collect(Collectors.toList());
    }

    public List<ApplaudDTO> getApplaudsByReceiverId(UUID receiverId) {
        return applaudRepository.findByReceiverId(receiverId)
                .stream()
                .map(applaudMapper::applaudToDTO)
                .collect(Collectors.toList());
    }

    public List<ApplaudDTO> getPublishedApplaudsByMemberEmail(String memberEmail) {
            return applaudRepository.findByReceiverAndIsPublished(
                    memberRepository.findByEmail(memberEmail),
                    true)
                    .stream()
                    .map(applaudMapper::applaudToDTO)
                    .collect(Collectors.toList());
    }

    public String getNumberOfUnreadApplaudsByMemberEmail(String memberEmail) {
            return applaudRepository.getNumberOfUnreadApplaudsByReceiverEmail(
                    memberRepository.findByEmail(memberEmail),
                    false);
    }

    public Applaud addApplaud(ApplaudDTO applaudDTO) {
        return applaudRepository.addApplaud(applaudMapper.dtoToApplaud(applaudDTO));
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

//    private Applaud validateApplaudData(ApplaudDTO applaudDTO) {
//        if (applaudDTO.getSender() == null) {
//            throw new IllegalArgumentException("Sender cannot be null");
//        }
//        if (applaudDTO.getReceiver() == null) {
//            throw new IllegalArgumentException("Receiver cannot be null");
//        }
//        if (applaudDTO.getComment() == null) {
//            throw new IllegalArgumentException("Comment cannot be null");
//        }
//        memberRepository.findById(applaudDTO.getSender().getId())
//                .orElseThrow(() -> new IllegalArgumentException("Sender with id: "+ applaudDTO.getSender().getId() +" does not exist"));
//        memberRepository.findById(applaudDTO.getReceiver().getId())
//                .orElseThrow(() -> new IllegalArgumentException("Receiver with id: "+ applaudDTO.getReceiver().getId() +" does not exist"));
//        return applaudMapper.dtoToApplaud(applaudDTO);
//    }
}
