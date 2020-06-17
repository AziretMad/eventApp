package com.company.eventApp.service;//package com.company.eventApp.service;

import com.company.eventApp.entity.User;
import com.company.eventApp.dto.UserDTO;
import com.company.eventApp.repository.UserRepo;
import com.company.eventApp.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public User create(UserDTO userDTO){
        User user = User.builder()
                .login(userDTO.getLogin())
                .email(userDTO.getEmail())
                .isActive(userDTO.getIsActive())
                .password(userDTO.getPassword())
                .telephone(userDTO.getTelephone())
                .build();
        return userRepo.save(user);
    }

    @Override
    public User getById(Long id){
        Optional<User> user = userRepo.findById(id);
        return user.get();
    }

    @Override
    public List<User> getAll(){
        return userRepo.findAll();
    }

    @Override
    public void delete(Long id) {
        userRepo.deleteById(id);
    }
}
