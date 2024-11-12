package com.toy.moviemate.domain.user.service;

import com.toy.moviemate.domain.admin.entity.Role;
import com.toy.moviemate.domain.admin.repository.RoleRepository;
import com.toy.moviemate.domain.user.dto.UserDto;
import com.toy.moviemate.domain.user.entity.User;
import com.toy.moviemate.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Transactional
    public void createUser(UserDto userDto) {
        Role role = roleRepository.findByRoleName("ROLE_USER");
        User user = User.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .age(userDto.getAge())
                .userRoles(new HashSet<>(Set.of(role)))
                .build();
        userRepository.save(user);
    }
}
