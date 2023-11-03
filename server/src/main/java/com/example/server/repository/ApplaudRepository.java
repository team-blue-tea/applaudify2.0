package com.example.server.repository;

import com.example.server.model.Applaud;
import com.example.server.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Repository
public class ApplaudRepository {

    private final JPAApplaudRepository applaudRepository;
    private final JPAMemberRepository memberRepository;

    @Autowired
    public ApplaudRepository(JPAApplaudRepository applaudRepository, JPAMemberRepository memberRepository) {
        this.applaudRepository = applaudRepository;
        this.memberRepository = memberRepository;
    }

    public List<Applaud> getApplauds() {
        return Streamable.of(applaudRepository.findAll()).toList();
    }

    public Applaud addApplaud(Applaud applaud) {
       return applaudRepository.save(applaud);
    }

    public List<Applaud> getApplaudsByReceiver(String receiverId) {
        UUID receiverUUID = UUID.fromString(receiverId);
        return applaudRepository.findByReceiverId(receiverUUID);
    }

    public void updateApplaud(Applaud updatedApplaud) {
        applaudRepository.save(updatedApplaud);
    }

    public Applaud getApplaudById(UUID applaudUUID) {
        return applaudRepository.findById(applaudUUID).get();
    }

    public List<Applaud> getPublishedApplaudsByMemberEmail(String memberEmail) {
        Member member = memberRepository.findByEmail(memberEmail);
        if (member != null) {
            return applaudRepository.findByReceiverAndIsPublished(member, true);
        } else {
            return Collections.emptyList();
        }
    }
}
