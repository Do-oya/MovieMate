package com.toy.moviemate.domain.admin.service;

import com.toy.moviemate.domain.admin.dto.RoleDto;
import com.toy.moviemate.domain.admin.entity.Role;

import java.util.List;

public interface RoleService {
    RoleDto getRoleDto(long id);
    List<Role> getRoles();
    List<Role> getRolesWithoutExpression();

    void createRole(RoleDto roleDto);

    void deleteRole(long id);
}