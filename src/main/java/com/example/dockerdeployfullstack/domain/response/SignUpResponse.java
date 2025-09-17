package com.example.dockerdeployfullstack.domain.response;


import com.example.dockerdeployfullstack.domain.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SignUpResponse {

    private Long id;
    private String message;

    private UserDto user;


}
