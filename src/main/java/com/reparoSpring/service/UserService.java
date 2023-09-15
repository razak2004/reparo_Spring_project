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
    private final UserMapper map = new UserMapper();
    private final Validation validate =  new Validation();

    public int createUser(UserRequestDto userDto) throws ServiceException{
        try {
            User user = map.mapRequestDtoToUser(userDto);
            validate.userCredentialValidation(user);
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

        return map.mapUserToResponse(user);

    }
    public UserResponseDto loginUser(UserRequestDto request) throws ServiceException{
        try {
            validate.loginCredentialValidation(request);
            User user = new User();
            if (userRepository != null) {
                User existUser = userRepository.findUserByNumber(request.getNumber());
                if(existUser==null)throw new ServiceException("User not present");
                if(!(existUser.getPassword().equals(request.getPassword())))throw new ServiceException("number or password is incorrect");
                user = existUser;
                user.setLogin(true);
                userRepository.save(user);
            }

            return map.mapUserToResponse(user);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }


    }

}
