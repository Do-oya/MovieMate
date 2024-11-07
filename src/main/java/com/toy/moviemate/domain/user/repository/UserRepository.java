package com.toy.moviemate.domain.user.repository;

import com.toy.moviemate.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
