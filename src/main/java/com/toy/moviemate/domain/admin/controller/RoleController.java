package com.toy.moviemate.domain.admin.controller;

import com.toy.moviemate.domain.admin.dto.RoleDto;
import com.toy.moviemate.domain.admin.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping("/admin/roles")
    public String getRoles(Model model) {
        model.addAttribute("roles", roleService.getRoles());
        return "admin/roles";
    }

    @GetMapping("/admin/roles/register")
    public String rolesRegister(Model model) {
        model.addAttribute("roles", new RoleDto());
        return "admin/roles-details";
    }

    @PostMapping("/admin/roles")
    public String createRole(RoleDto roleDto) {
        roleService.createRole(roleDto);
        return "redirect:/admin/roles";
    }

    @GetMapping("/admin/roles/{id}")
    public String getRole(@PathVariable("id") Long id, Model model) {
        model.addAttribute("roles", roleService.getRoleDto(id));
        return "admin/roles-details";
    }

    @GetMapping("/admin/roles/delete/{id}")
    public String removeRoles(@PathVariable("id") Long id) {
        roleService.deleteRole(id);
        return "redirect:/admin/roles";
    }
}