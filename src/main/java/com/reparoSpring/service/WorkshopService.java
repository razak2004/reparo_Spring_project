package com.reparoSpring.service;

import com.reparoSpring.Validation.Validation;
import com.reparoSpring.datamapper.WorkshopMapper;
import com.reparoSpring.dto.workshop.WorkshopRequestDto;
import com.reparoSpring.dto.workshop.WorkshopResponseDto;
import com.reparoSpring.exception.ServiceException;
import com.reparoSpring.exception.ValidationException;
import com.reparoSpring.model.User;
import com.reparoSpring.model.Workshop;
import com.reparoSpring.repository.UserRepository;
import com.reparoSpring.repository.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkshopService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WorkshopRepository workshopRepository;

    @Autowired
    private UserService userService;


    private final Validation validation = new Validation();


    private final WorkshopMapper map =  new WorkshopMapper();



    public int createWorkshop(WorkshopRequestDto dto) throws ServiceException{
        int id = 0 ;
        Workshop workshop = map.mapRequestToWorkshop(dto);
        try {
            validation.workshopValidation(workshop);
            if(userRepository!=null&&workshopRepository!=null){
                userService.isUserExist(dto.getUserId());
                User user = userRepository.findUserById(dto.getUserId());
                workshop.setUser(user);
             Workshop work = workshopRepository.save(workshop);
             id = work.getWorkShopId();
            }
        } catch (ValidationException e) {
            throw new ServiceException(e.getMessage());
        }
        return  id;
    }
    public List<WorkshopResponseDto> getAllWorkshops(int id)throws ServiceException{
        try {
            List<WorkshopResponseDto> workshops =  new ArrayList<>();
            userService.isUserExist(id);
            if(workshopRepository!=null){
                List<Workshop> workshops1 =  workshopRepository.findAll();
                for (Workshop work :workshops1) {
                    workshops.add(map.mapWorkshopToResponse(work));
                }

            }
            return workshops;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }

    }


}
