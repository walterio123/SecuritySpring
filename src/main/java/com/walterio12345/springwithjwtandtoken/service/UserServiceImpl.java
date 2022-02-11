package com.walterio12345.springwithjwtandtoken.service;

import com.walterio12345.springwithjwtandtoken.domain.Role;
import com.walterio12345.springwithjwtandtoken.domain.User;
import com.walterio12345.springwithjwtandtoken.repo.RoleRepo;
import com.walterio12345.springwithjwtandtoken.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {


    private final  PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepo.findByUsername(username);
        if(user == null){
            log.error("User not found in te db");
            throw new UsernameNotFoundException("User not found in te db");
        }else{
            log.info("User found in te db: {}",username);
        }
        Collection<SimpleGrantedAuthority>authorities=new ArrayList<>();
        user.getRoles().forEach(role ->{
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails
                .User( user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public User saveUser(User user) {
        log.info("Saving new User {} to the db",user.getName());
       user.setPassword(passwordEncoder.encode(user.getPassword()));
             return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new Role {}to the db",role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Addving Role {} to user {} to the db", roleName,username);
        User user= userRepo.findByUsername(username);
         Role role=roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        log.info("Fetching User {}",username);
        return userRepo.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching Users ");
        return userRepo.findAll();
    }
    public List<Role> getRoles() {
        return roleRepo.findAll();
    }


}
