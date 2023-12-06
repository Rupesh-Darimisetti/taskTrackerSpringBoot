package com.example.taskproject.serviceImplementation;

import com.example.taskproject.entity.Users;
import com.example.taskproject.payload.UserDTO;
import com.example.taskproject.repository.UserRepository;
import com.example.taskproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        // userDTO is not an entity to users
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        Users user = userDtoToEntity(userDTO); // converted userDTO to Users
        Users savedUser = userRepository.save(user);
        return entityToUserDTO(savedUser);
    }
    private Users userDtoToEntity(UserDTO userDTO){
        Users users = new Users();
        users.setName(userDTO.getName());
        users.setPassword(userDTO.getPassword());
        users.setEmail(userDTO.getEmail());
        return users;
    }

    private UserDTO entityToUserDTO(Users user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }
}
