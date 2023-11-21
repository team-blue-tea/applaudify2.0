package com.example.server.repository;

import com.example.server.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class MemberRepository {

    private final JPAMemberRepository memberRepository;

    @Autowired
    public MemberRepository(JPAMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> getMembers() {
        return Streamable.of(memberRepository.findAll()).toList();
    }

    public void addMember(Member member) {
        memberRepository.save(member);
    }

    public Member getMemberById(UUID memberUUID) {
        return memberRepository.findById(memberUUID).get();
    }

    public void updateMember(Member updatedMember) {
        memberRepository.save(updatedMember);
    }

    public Member findByEmail(String memberEmail) {
        return memberRepository.findByEmail(memberEmail);
    }

    public Optional<Member> findById(UUID memberId) { return memberRepository.findById(memberId);}
}
