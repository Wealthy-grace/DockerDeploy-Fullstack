package com.example.dockerdeployfullstack.mapper;

import com.example.dockerdeployfullstack.persistence.Entity.Role;
import com.example.dockerdeployfullstack.persistence.Entity.UserEntity;
import com.example.dockerdeployfullstack.domain.dto.UserDto;
import com.example.dockerdeployfullstack.domain.request.SignUpRequest;

public class UserMapper {

    public static UserDto ToMapUserDto(UserEntity userEntity) {
        return UserDto.builder()
                .id(userEntity.getId())
                .fullName(userEntity.getFullName())
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .email(userEntity.getEmail())
                .role(userEntity.getRole().name())
                .image(userEntity.getImage())
                .build();
    }


    public static UserEntity ToMapUserEntity(SignUpRequest signUpRequest) {
        return UserEntity.builder()
                .fullName(signUpRequest.getFullName())
                .username(signUpRequest.getUsername())
                .password(signUpRequest.getPassword())
                .email(signUpRequest.getEmail())
                .role(Role.ROLE_USER)
                .image(signUpRequest.getImage())
                .build();

    }
}
