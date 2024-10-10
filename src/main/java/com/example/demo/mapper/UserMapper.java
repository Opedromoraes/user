package com.example.demo.mapper;

import com.example.demo.controller.user.request.UserRequest;
import com.example.demo.controller.user.response.UserResponse;
import com.example.demo.domain.dto.UserDTO;
import com.example.demo.domain.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO entityToDTO(User entity);

    User dtoToEntity(UserDTO dto);

    UserDTO requestToDto(UserRequest request);

    UserResponse dtoToResponse(UserDTO dto);


}
