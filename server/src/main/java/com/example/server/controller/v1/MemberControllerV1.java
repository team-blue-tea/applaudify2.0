package com.example.server.controller.v1;

import com.example.server.model.Member;
import com.example.server.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/members")
@CrossOrigin("*")
public class MemberControllerV1 {

    private final MemberRepository repo;

    @Autowired
    public MemberControllerV1(MemberRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Member> getMembers() {
        return repo.getMembers();
    }

    @PostMapping
    public String addMember(@RequestBody Member member) {
        repo.addMember(member);
        return "Member with id: " + member.getId() + " successfully added to DB";
    }

    @PutMapping("/{id}")
    public String updateMember(@PathVariable("id") String memberId, @RequestBody Member updatedMember) {
        UUID memberUUID = UUID.fromString(memberId);
        Member existingMember = repo.getMemberById(memberUUID);

        if (existingMember != null) {
            updateMemberFields(existingMember, updatedMember);
            repo.updateMember(existingMember);

            return "Member with id: " + memberId + " successfully updated";
        } else {
            return "Member with id: " + memberId + " not found";
        }
    }

    private void updateMemberFields(Member existingMember, Member updatedMember) {
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
    }
}
