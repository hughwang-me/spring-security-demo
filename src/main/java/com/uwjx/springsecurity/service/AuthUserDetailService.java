package com.uwjx.springsecurity.service;

import com.uwjx.springsecurity.dao.UserMapper;
import com.uwjx.springsecurity.domain.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;

@Component
@Slf4j
public class AuthUserDetailService implements UserDetailsService {

    @Resource
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        UserEntity queryUserEntity = new UserEntity();
        queryUserEntity.setUsername(s);
        UserEntity userEntity = userMapper.selectOne(queryUserEntity);
        if(userEntity == null){
            log.warn("用户不存在");
            throw new UsernameNotFoundException("User " + s + " was not found in db");
        }
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");
        grantedAuthorities.add(grantedAuthority);

        User user = new User(s, userEntity.getPassword() , grantedAuthorities);
        return user;
    }
}
