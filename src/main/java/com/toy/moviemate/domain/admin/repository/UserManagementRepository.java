package com.toy.moviemate.domain.admin.repository;

import com.toy.moviemate.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserManagementRepository extends JpaRepository<User, Long> {
}
