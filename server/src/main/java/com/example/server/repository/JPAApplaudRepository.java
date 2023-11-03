package com.example.server.repository;

import com.example.server.model.Applaud;
import com.example.server.model.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface JPAApplaudRepository extends CrudRepository<Applaud, UUID> {
    List<Applaud> findByReceiverId(UUID receiverId);

    List<Applaud> findByReceiverAndIsPublished(Member receiver, boolean isPublished);

    List <Applaud> findByReceiverAndIsRead(Member member, boolean isRead);
}
