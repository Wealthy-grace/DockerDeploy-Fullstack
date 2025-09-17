package com.example.dockerdeployfullstack.business.impl;

import com.example.dockerdeployfullstack.persistence.Entity.UserEntity;
import com.example.dockerdeployfullstack.business.UserService;
import com.example.dockerdeployfullstack.domain.dto.UserDto;
import com.example.dockerdeployfullstack.domain.request.LoginRequest;
import com.example.dockerdeployfullstack.domain.request.SignUpRequest;
import com.example.dockerdeployfullstack.domain.response.LonginResponse;
import com.example.dockerdeployfullstack.domain.response.SignUpResponse;
import com.example.dockerdeployfullstack.exceptions.UserAlreadyExistsException;
import com.example.dockerdeployfullstack.mapper.UserMapper;
import com.example.dockerdeployfullstack.persistence.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    //private final UserMapper userMapper;

    private final UserRepo userRepository;
    @Override
    public SignUpResponse signUp(SignUpRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UserAlreadyExistsException("User already exists with username: " + request.getUsername());
        }

        log.info("Creating new book: {}", request.getFullName());

        // Check if the contact already exists to avoid duplicates
        checkExistingBook(request);
        // Map the incoming request to a database entity
        UserEntity entity = UserMapper.ToMapUserEntity(request);
        // Save the new contact entity in the database
        UserEntity savedEntity = userRepository.save(entity);
        log.info("Contact created successfully with ID: {}", savedEntity.getId());
        // Return a response with the ID and success message
        return SignUpResponse.builder()
                .id(savedEntity.getId())
                .user(UserMapper.ToMapUserDto(savedEntity))
                .message("User created successfully")
                .build();

    }
    @Override
    public LonginResponse login(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public UserDto getUserByUsername(String username) {
        return null;
    }

    @Override
    public UserDto getUserById(Long id) {
        return null;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }

    private void checkExistingBook(SignUpRequest request) {
        if (userRepository.existsByFullName(request.getFullName())) {
            throw new UserAlreadyExistsException("User already exists with name: " + request.getFullName());

        }

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UserAlreadyExistsException("User already exists with username: " + request.getUsername());

        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("User already exists with email: " + request.getEmail());

        }

    }
}
