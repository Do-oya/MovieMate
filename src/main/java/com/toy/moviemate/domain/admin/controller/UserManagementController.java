package com.toy.moviemate.domain.admin.controller;

import com.toy.moviemate.domain.admin.entity.Role;
import com.toy.moviemate.domain.admin.service.RoleService;
import com.toy.moviemate.domain.admin.service.UserManagementService;
import com.toy.moviemate.domain.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserManagementController {

    private final UserManagementService userManagementService;
    private final RoleService roleService;

    @GetMapping("/admin/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userManagementService.getUsers());
        return "admin/users";
    }

    @PostMapping("/admin/users")
    public String modifyUser(UserDto userDto) {
        userManagementService.modifyUser(userDto);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/users/{id}")
    public String getUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userManagementService.getUser(id));
        model.addAttribute("roleList", roleService.getRolesWithoutExpression());
        return "admin/user-details";
    }

    @GetMapping("/admin/users/delete/{id}")
    public String removeUser(@PathVariable("id") Long id) {
        userManagementService.deleteUser(id);
        return "redirect:/admin/users";
    }
}