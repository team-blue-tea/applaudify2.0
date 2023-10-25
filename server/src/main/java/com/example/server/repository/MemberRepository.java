package com.example.server.repository;

import com.example.server.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import java.util.List;

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

}
