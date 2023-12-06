package com.example.server.controller.v1;

import com.example.server.model.Member;
import com.example.server.model.dto.MemberRequestDTO;
import com.example.server.repository.MemberRepository;
import com.example.server.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/members")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MemberControllerV1 {

    private final MemberService memberService;

    @Autowired
    public MemberControllerV1(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public List<Member> getMembers() {
        return memberService.getMembers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Member addMember(@Valid @RequestBody MemberRequestDTO memberRequestDTO) {
        return memberService.addMember(memberRequestDTO);
    }

    @PutMapping("/{id}")
    public Member updateMember(@PathVariable("id") UUID memberId, @RequestBody Member updatedMember) {
          return memberService.updateMember(memberId, updatedMember);
    }
}
