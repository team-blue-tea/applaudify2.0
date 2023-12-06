package com.example.server.service;
import com.example.server.model.Applaud;
import com.example.server.model.dto.ApplaudDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = MemberMapper.class, componentModel = "spring")
public interface ApplaudMapper {

    ApplaudMapper INSTANCE = Mappers.getMapper(ApplaudMapper.class);

    @Mapping(target = "sender", source = "sender")
    @Mapping(target = "receiver", source = "receiver")
    ApplaudDTO applaudToDTO(Applaud applaud);

    @Mapping(target = "sender", source = "sender")
    @Mapping(target = "receiver", source = "receiver")
    Applaud dtoToApplaud(ApplaudDTO applaudDTO);
}

