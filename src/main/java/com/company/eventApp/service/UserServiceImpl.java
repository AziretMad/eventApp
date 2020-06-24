package com.company.eventApp.service;

import com.company.eventApp.entity.Role;
import com.company.eventApp.entity.User;
import com.company.eventApp.dto.UserDTO;
import com.company.eventApp.repository.RoleRepo;
import com.company.eventApp.repository.UserRepo;
import com.company.eventApp.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public User create(UserDTO userDTO){
        User user = User.builder()
                .login(userDTO.getLogin())
                .email(userDTO.getEmail())
                .isActive(userDTO.getIsActive())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .telephone(userDTO.getTelephone())
                .build();
        Set<Role> roles = new HashSet<>();
        String userRole = "USER";
        Role role = roleRepo.findByRoleName(userRole);
        roles.add(role);
        user.setRoles(roles);
        Set<User> users = new HashSet<>();
        users.add(user);
        if(role.getUsers() == null){
            role.setUsers(users);
        }
        else {
            role.getUsers().add(user);
        }
        return userRepo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepo.findByLogin(login);
        if(user == null){
            throw new UsernameNotFoundException("Invalid login or password");
        }
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), getAuthority(user) );
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user){
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        });
        return authorities;
    }

    @Override
    public User getById(Long id){
        Optional<User> user = userRepo.findById(id);
        return user.get();
    }

    public UserDTO wrapDTO(User user) {
        UserDTO userDTO = UserDTO.builder()
                .login(user.getLogin())
                .email(user.getEmail())
                .password(user.getPassword())
                .telephone(user.getTelephone())
                .build();
        Set<Role> roles = user.getRoles();
        Set<String> roleNames = new HashSet<>();
        for(Role role : roles){
            roleNames.add(role.getRoleName());
        }
        userDTO.setRoles(roleNames);
        return userDTO;
    }

    @Override
    public List<User> getAll(){
        return userRepo.findAll();
    }

    @Override
    public void delete(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public User update(User entity) {
        return userRepo.save(entity);
    }

    public User getByLogin(String login){
        return userRepo.findByLogin(login);
    }
}
