package com.toy.moviemate.security.service;

import com.toy.moviemate.domain.admin.entity.Role;
import com.toy.moviemate.domain.admin.service.RoleService;
import com.toy.moviemate.security.dto.UserContext;
import com.toy.moviemate.domain.user.dto.UserDto;
import com.toy.moviemate.domain.user.entity.User;
import com.toy.moviemate.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service("userDetailsService")
@RequiredArgsConstructor
public class FormUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with username: " + username);
        }
        List<GrantedAuthority> authorities = user.getUserRoles()
                .stream()
                .map(Role::getRoleName)
                .collect(Collectors.toSet())
                .stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        ModelMapper mapper = new ModelMapper();
        UserDto userDto = mapper.map(user, UserDto.class);
        return new UserContext(userDto, authorities);
    }
}
