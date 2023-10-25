package com.example.server.controller.v1;

import com.example.server.model.Member;
import com.example.server.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
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
        return member.getName() + " successfully added to DB";
    }
}
