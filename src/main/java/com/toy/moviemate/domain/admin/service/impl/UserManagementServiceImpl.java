package com.toy.moviemate.domain.admin.service.impl;

import com.toy.moviemate.domain.admin.entity.Role;
import com.toy.moviemate.domain.admin.repository.RoleRepository;
import com.toy.moviemate.domain.admin.repository.UserManagementRepository;
import com.toy.moviemate.domain.admin.service.UserManagementService;
import com.toy.moviemate.domain.user.dto.UserDto;
import com.toy.moviemate.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserManagementServiceImpl implements UserManagementService {

    private final UserManagementRepository userManagementRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public void modifyUser(UserDto userDto) {
        User existingUser = userManagementRepository.findById(userDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userDto.getId()));
        Set<Role> roles = getRolesFromDto(userDto);
        User updatedUser = existingUser.toBuilder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .age(userDto.getAge())
                .userRoles(roles)
                .build();
        userManagementRepository.save(updatedUser);
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto getUser(Long id) {
        User user = userManagementRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
        UserDto userDto = modelMapper.map(user, UserDto.class);
        List<String> roles = user.getUserRoles().stream()
                .map(Role::getRoleName)
                .collect(Collectors.toList());
        userDto.setRoles(roles);
        return userDto;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getUsers() {
        return userManagementRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        userManagementRepository.deleteById(id);
    }

    private Set<Role> getRolesFromDto(UserDto userDto) {
        if (userDto.getRoles() == null) {
            return Set.of();
        }
        return userDto.getRoles().stream()
                .map(roleRepository::findByRoleName)
                .collect(Collectors.toSet());
    }
}