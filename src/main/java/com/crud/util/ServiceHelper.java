package com.crud.util;

import com.crud.entity.User;
import com.crud.repository.UserRepo;
import org.modelmapper.ModelMapper;

import java.util.Optional;

public class ServiceHelper {
    private UserRepo userRepo;
    private ModelMapper modelMapper;

    public ServiceHelper(UserRepo userRepo) {
        this.userRepo = userRepo;
        this.modelMapper = new ModelMapper();
    }

    public User updateHelper(User user){
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

}
