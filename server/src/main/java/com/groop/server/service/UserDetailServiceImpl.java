package com.groop.server.service;

import com.groop.server.domain.Member;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author joandy alejo garcia
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = new Member();
        member.setUsername(username);
        member.setPassword("password");
        member.setId(1l);
        return member;
    }
}
