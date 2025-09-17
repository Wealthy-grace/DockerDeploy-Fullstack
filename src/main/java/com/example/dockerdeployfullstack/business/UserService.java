package com.example.dockerdeployfullstack.business;

import com.example.dockerdeployfullstack.domain.dto.UserDto;
import com.example.dockerdeployfullstack.domain.request.LoginRequest;
import com.example.dockerdeployfullstack.domain.response.LonginResponse;

import com.example.dockerdeployfullstack.domain.request.SignUpRequest;
import com.example.dockerdeployfullstack.domain.response.SignUpResponse;

public interface UserService {

    SignUpResponse signUp(SignUpRequest signUpRequest);

    LonginResponse login(LoginRequest loginRequest);

    UserDto getUserByUsername(String username);

    UserDto getUserById(Long id);

    UserDto updateUser(UserDto userDto);

    void deleteUser(Long id);


}
