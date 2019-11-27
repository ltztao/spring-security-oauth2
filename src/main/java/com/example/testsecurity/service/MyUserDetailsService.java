package com.example.testsecurity.service;

import com.example.testsecurity.mapper.PermissionMapper;
import com.example.testsecurity.mapper.UserMapper;
import com.example.testsecurity.model.Permission;
import com.example.testsecurity.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

    @Autowired
    private UserMapper       userService;
    @Autowired
    private PermissionMapper permissionService;

    public UserDetails loadUserByUsername(String username){

        User user = userService.findUserByUserName(username);
        if(user != null) {
            List<Permission> permissions = permissionService.findPermissionByUserId(user.getUserId());
            user.setPermissions(permissions);
            return  user;
        }
        return null;
    }

}
