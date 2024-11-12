package com.toy.moviemate.domain.admin.service;

import com.toy.moviemate.domain.user.dto.UserDto;
import com.toy.moviemate.domain.user.entity.User;

import java.util.List;

public interface UserManagementService {
    List<User> getUsers();
    UserDto getUser(Long id);
    void modifyUser(UserDto userDto);
    void deleteUser(Long id);
}