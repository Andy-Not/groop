package com.groop.server.service;

import com.groop.server.domain.Member;
import com.groop.server.util.CustomPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author joandy alejo garcia
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private CustomPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = new Member();
        member.setUsername(username);
        member.setPassword(passwordEncoder.getPasswordEncoder().encode("password"));
        member.setId(1l);
        return member;
    }
}
