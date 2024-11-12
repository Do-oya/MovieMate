package com.toy.moviemate.domain.admin.service.impl;

import com.toy.moviemate.domain.admin.dto.RoleDto;
import com.toy.moviemate.domain.admin.entity.Role;
import com.toy.moviemate.domain.admin.repository.RoleRepository;
import com.toy.moviemate.domain.admin.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    @Override
    public RoleDto getRoleDto(long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Role not found with id: " + id));
        return modelMapper.map(role, RoleDto.class);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Role> getRolesWithoutExpression() {
        return roleRepository.findAllRolesWithoutExpression();
    }

    @Transactional
    @Override
    public void createRole(RoleDto roleDto) {
        Role role = modelMapper.map(roleDto, Role.class);
        roleRepository.save(role);
    }

    @Transactional
    @Override
    public void deleteRole(long id) {
        roleRepository.deleteById(id);
    }
}