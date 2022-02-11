package com.walterio12345.springwithjwtandtoken.service;

import com.walterio12345.springwithjwtandtoken.domain.Role;
import com.walterio12345.springwithjwtandtoken.domain.User;

import java.util.List;

public interface
UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username,String roleName);
    User getUser(String username);
    List<User> getUsers();
}
