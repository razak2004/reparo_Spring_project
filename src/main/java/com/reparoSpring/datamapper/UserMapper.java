package com.reparoSpring.datamapper;

import com.reparoSpring.dto.user.UserRequestDto;
import com.reparoSpring.dto.user.UserResponseDto;
import com.reparoSpring.model.User;

public class UserMapper {
    public User mapRequestDtoToUser(UserRequestDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setNumber(dto.getNumber());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        return user;
    }

    public UserResponseDto mapUserToResponse(User user){
        return new UserResponseDto(user.getId(), user.getName(), user.getNumber(), user.getRole());
    }


}
