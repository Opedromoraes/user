package com.example.demo.controller.user;

import com.example.demo.controller.user.request.UserRequest;
import com.example.demo.controller.user.response.UserResponse;
import com.example.demo.domain.dto.UserDTO;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class UserController implements IUserController {

    private final UserService service;
    private final UserMapper mapper;

    @Override
    public ResponseEntity<UserResponse> create(UserRequest request) {
        UserDTO userDTO = service.createUser(mapper.requestToDto(request));
        UserResponse response = mapper.dtoToResponse(userDTO);
        return ResponseEntity.status(CREATED).body(response);
    }

    @Override
    public ResponseEntity<UserResponse> findById(Long id) {
        UserDTO userDTO = service.findById(id);
        UserResponse response = mapper.dtoToResponse(userDTO);
        return ResponseEntity.status(OK).body(response);
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        String response = service.deleteUser(id);
        return ResponseEntity.status(OK).body(response);
    }
}
