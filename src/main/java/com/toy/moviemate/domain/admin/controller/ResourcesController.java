package com.toy.moviemate.domain.admin.controller;

import com.toy.moviemate.domain.admin.dto.ResourcesDto;
import com.toy.moviemate.domain.admin.service.ResourcesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ResourcesController {

    private final ResourcesService resourcesService;

    // 모든 리소스를 조회
    @GetMapping("/admin/resources")
    public String getResources(Model model) {
        model.addAttribute("resources", resourcesService.getAllResources());
        return "admin/resources";
    }

    @PostMapping("/admin/resources")
    public String createResources(ResourcesDto resourcesDto) {
        resourcesService.saveResource(resourcesDto);
        return "redirect:/admin/resources";
    }

    @GetMapping("/admin/resources/register")
    public String resourcesRegister(Model model) {
        model.addAttribute("roleList", resourcesService.getAllRoles());
        model.addAttribute("resources", new ResourcesDto());
        return "admin/resources-details";
    }

    @GetMapping("/admin/resources/{id}")
    public String resourceDetails(@PathVariable("id") Long id, Model model) {
        model.addAttribute("resources", resourcesService.getResourceDto(id));
        model.addAttribute("roleList", resourcesService.getAllRoles());
        model.addAttribute("myRoles", resourcesService.getResourceRoleNames(id));
        return "admin/resources-details";
    }

    @GetMapping("/admin/resources/delete/{id}")
    public String removeResource(@PathVariable("id") Long id) {
        resourcesService.deleteResource(id);
        return "redirect:/admin/resources";
    }
}