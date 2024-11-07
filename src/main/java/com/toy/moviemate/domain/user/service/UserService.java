package com.toy.moviemate.domain.user.service;

import com.toy.moviemate.domain.user.dto.UserDto;
import com.toy.moviemate.domain.user.entity.User;
import com.toy.moviemate.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void createUser(UserDto userDto) {
        User user = User.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .age(userDto.getAge())
                .roles(userDto.getRoles())
                .build();
        userRepository.save(user);
    }
}
