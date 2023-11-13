package com.app.registration.service;

import com.app.registration.dto.UserDTO;
import com.app.registration.model.User;
import com.app.registration.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDTO createUser(UserDTO userDTO) {

        User user = User.builder()
                            .firstName(userDTO.getFirstName())
                            .lastName(userDTO.getLastName())
                            .email(userDTO.getEmail())
                            .phone(userDTO.getPhone())
                            .build();
        // 1. save user
        user = this.userRepository.save(user);

        //2. return userDTO
        return UserDTO.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .build();
    }
}
