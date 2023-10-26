package com.example.server.repository;

import com.example.server.model.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface JPAMemberRepository extends CrudRepository<Member, UUID> {
}
