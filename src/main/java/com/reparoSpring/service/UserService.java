package com.reparoSpring.service;

import com.reparoSpring.Validation.Validation;
import com.reparoSpring.datamapper.UserMapper;
import com.reparoSpring.dto.user.UserRequestDto;
import com.reparoSpring.dto.user.UserResponseDto;
import com.reparoSpring.exception.ServiceException;
import com.reparoSpring.exception.ValidationException;
import com.reparoSpring.model.User;
import com.reparoSpring.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService{
    public UserService() {
    }

    @Autowired
    private  UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public int createUser(UserRequestDto userDto) throws ServiceException{
        Validation validation = new Validation();
        UserMapper  map =  new UserMapper();
        try {
            User user = map.mapRequestDtoToUser(userDto);
            validation.userCredentialValidation(user);
            int id = 0 ;
            if(userRepository!=null){
                User existUser =  userRepository.findUserByNumber(user.getNumber());
                if(existUser!=null)throw new ServiceException("User Already present");
                User user1 = userRepository.save(user);
                id = user1.getId();
            }


            return id;
        } catch (ValidationException e) {
            throw new ServiceException(e.getMessage());
        }

    }
    public UserResponseDto findUserByNumber(long number) throws ServiceException{
        User user = new User();
        if (userRepository != null) {
            User existUser = userRepository.findUserByNumber(number);
            if(existUser==null)throw new ServiceException("User not present");
            user = existUser;
        }
        UserMapper map = new UserMapper();
        return map.mapUserToResponse(user);

    }

}
