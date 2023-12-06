package com.example.server.service;

import com.example.server.controller.v1.exception.MemberNotFoundException;
import com.example.server.model.Member;
import com.example.server.model.dto.MemberRequestDTO;
import com.example.server.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Autowired
    public MemberService(MemberRepository memberRepository, MemberMapper memberMapper){
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }

    public List<Member> getMembers() {
        return memberRepository.getMembers();
    }

    public Member updateMember(UUID memberId, Member updatedMember) {
        return memberRepository.findById(memberId)
                .map(member -> updateMemberFields(member, updatedMember))
                .map(memberRepository::updateMember)
                .orElseThrow(() -> new MemberNotFoundException(memberId));
    }

    public Member addMember(MemberRequestDTO memberRequestDTO) {
        return memberRepository.addMember(memberMapper.requestDTOtoMember(memberRequestDTO));
    }

    private Member updateMemberFields(Member existingMember, Member updatedMember) {
        if (updatedMember.getName() != null) {
            existingMember.setName(updatedMember.getName());
        }
        if (updatedMember.getJobTitle() != null) {
            existingMember.setJobTitle(updatedMember.getJobTitle());
        }
        if (updatedMember.getCompany() != null) {
            existingMember.setCompany(updatedMember.getCompany());
        }
        if (updatedMember.getEmail() != null) {
            existingMember.setEmail(updatedMember.getEmail());
        }
        if (updatedMember.getAvatarUrl() != null) {
            existingMember.setAvatarUrl(updatedMember.getAvatarUrl());
        }
        if (updatedMember.getBio() != null) {
            existingMember.setBio(updatedMember.getBio());
        }
        if (updatedMember.getSkills() != null) {
            existingMember.setSkills(updatedMember.getSkills());
        }
        if (updatedMember.getExperience() != null) {
            existingMember.setExperience(updatedMember.getExperience());
        }
        return existingMember;
    }
}
