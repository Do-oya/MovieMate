package com.toy.moviemate.domain.admin.service;

import com.toy.moviemate.domain.admin.dto.ResourcesDto;
import com.toy.moviemate.domain.admin.entity.Resources;
import com.toy.moviemate.domain.admin.entity.Role;

import java.util.List;

public interface ResourcesService {
    List<Resources> getAllResources();
    ResourcesDto getResourceDto(Long id);
    List<Role> getAllRoles();
    List<String> getResourceRoleNames(Long id);
    void saveResource(ResourcesDto resourcesDto);
    void deleteResource(Long id);
}