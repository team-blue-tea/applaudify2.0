package com.example.server.repository;

import com.example.server.model.Member;
import org.springframework.data.repository.CrudRepository;

public interface JPAMemberRepository extends CrudRepository<Member, String> {
}
