package com.example.server.service;

import com.example.server.model.Applaud;
import com.example.server.model.Member;
import com.example.server.repository.ApplaudRepository;
import com.example.server.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
}
