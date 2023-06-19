package com.crud.service;


import com.crud.entity.User;
import com.crud.model.UserDTO;
import com.crud.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

   private UserRepo userRepo;
   private ModelMapper modelMapper;
   PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.modelMapper = new ModelMapper();
    }

    public User createUser(User user){
        String encodedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword((encodedPassword));
        return userRepo.save(user);
    }
    public UserDTO getUserById(int id){
        return (UserDTO) userRepo.findById(id)
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }
    public List<UserDTO> getAllUsers(){
        return userRepo.findAll()
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
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
//    Converting DAO to DTO manually!!!
//    private UserDTO convertEntityToDTO(User user){
//        UserDTO userDTO = new UserDTO();
//        userDTO.setUserId(user.getId());
//        userDTO.setEmail(user.getEmail());
//        userDTO.setAge(user.getAge());
//        userDTO.setName(user.getName());
//        return userDTO;
//    }

    //Converting DAO to DTO with the use of the model mapper dependency
    private UserDTO convertEntityToDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
    }

}
