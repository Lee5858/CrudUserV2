package com.crud.service;


import com.crud.entity.User;
import com.crud.repository.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

   private UserRepo userRepo;
   PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User createUser(User user){
        String encodedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword((encodedPassword));
        return userRepo.save(user);
    }
    public User getUserById(int id){
        return userRepo.findById(id).orElse(null);
    }
    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public User updateUser(User user){
        User oldUser = null;
        Optional<User> optionalUser = userRepo.findById(user.getId());
        if(optionalUser.isPresent()){
            oldUser = optionalUser.get();
            oldUser.setName(user.getName());
            oldUser.setAge(user.getAge());
            oldUser.setEmail(user.getEmail());
            oldUser.setPassword(user.getPassword());
            userRepo.save(oldUser);
        }else{
            return new User();
        }
        return oldUser;
    }

    public String delUserById(int id){
        userRepo.deleteById(id);
        return "User Deleted!!!";
    }

}
