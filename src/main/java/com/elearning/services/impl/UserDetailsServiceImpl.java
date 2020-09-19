package com.elearning.services.impl;

import com.elearning.entity.Users;
import com.elearning.repository.UserRepositories;
import com.elearning.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepositories userRepositories;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = userRepositories.findByEmail(email);
        if (user == null) throw new UsernameNotFoundException("No User Found!");
        List<GrantedAuthority> authorities = new ArrayList<>();
        String roleName = user.getRole().getName();
        authorities.add(new SimpleGrantedAuthority(roleName));

        return new CustomUserDetails(user.getEmail(), user.getPassword(), authorities);
    }
}
