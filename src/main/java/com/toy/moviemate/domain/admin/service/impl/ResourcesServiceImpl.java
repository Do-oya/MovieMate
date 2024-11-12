package com.toy.moviemate.domain.admin.service.impl;

import com.toy.moviemate.domain.admin.dto.ResourcesDto;
import com.toy.moviemate.domain.admin.entity.Resources;
import com.toy.moviemate.domain.admin.entity.Role;
import com.toy.moviemate.domain.admin.repository.ResourcesRepository;
import com.toy.moviemate.domain.admin.repository.RoleRepository;
import com.toy.moviemate.domain.admin.service.ResourcesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResourcesServiceImpl implements ResourcesService {

    private final ResourcesRepository resourcesRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    @Override
    public List<Resources> getAllResources() {
        return resourcesRepository.findAll(Sort.by(Sort.Order.asc("orderNum")));
    }

    @Transactional(readOnly = true)
    @Override
    public ResourcesDto getResourceDto(Long id) {
        Resources resources = resourcesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Resource not found with id: " + id));
        return modelMapper.map(resources, ResourcesDto.class);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<String> getResourceRoleNames(Long id) {
        Resources resources = resourcesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Resource not found with id: " + id));
        return resources.getRoleSet().stream()
                .map(Role::getRoleName)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void saveResource(ResourcesDto resourcesDto) {
        Resources resources = modelMapper.map(resourcesDto, Resources.class);
        Role role = roleRepository.findByRoleName(resourcesDto.getRoleName());
        if (role == null) {
            throw new IllegalArgumentException("Role not found with name: " + resourcesDto.getRoleName());
        }
        resources.setRoleSet(Set.of(role));
        resourcesRepository.save(resources);
    }

    @Transactional
    @Override
    public void deleteResource(Long id) {
        resourcesRepository.deleteById(id);
    }
}