package com.example.demo.service;

import com.example.demo.domain.dto.UserDTO;
import com.example.demo.domain.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public UserDTO createUser(UserDTO userDTO) {
        encodePassword(userDTO);
        validateEmail(userDTO);
        User user = repository.save(mapper.dtoToEntity(userDTO));
        return mapper.entityToDTO(user);
    }

    public UserDTO findById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return mapper.entityToDTO(user);
    }

    public String deleteUser(Long id) {
        if (findById(id) == null) {
            throw new RuntimeException("User not found");
        }
        repository.deleteById(id);
        return "User successfully deleted";
    }

    public void validateEmail(UserDTO userDTO) {
        if (repository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new RuntimeException("This e-mail already exists");
        }
    }


    public void encodePassword(UserDTO userDTO) {
        String encryptedPassword = passwordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(encryptedPassword);
    }

}
