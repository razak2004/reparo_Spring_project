package com.reparoSpring.service;

import com.reparoSpring.Validation.Validation;
import com.reparoSpring.datamapper.WorkshopMapper;
import com.reparoSpring.dto.workshop.WorkshopRequestDto;
import com.reparoSpring.exception.ServiceException;
import com.reparoSpring.exception.ValidationException;
import com.reparoSpring.model.User;
import com.reparoSpring.model.Workshop;
import com.reparoSpring.repository.UserRepository;
import com.reparoSpring.repository.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkshopService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WorkshopRepository workshopRepository;

    private final Validation validation =  new Validation();

    private final WorkshopMapper map =  new WorkshopMapper();

    public int createWorkshop(WorkshopRequestDto dto) throws ServiceException{
        int id = 0 ;
        Workshop workshop = map.mapRequestToWorkshop(dto);
        try {
            validation.workshopValidation(workshop);
            if(userRepository!=null&&workshopRepository!=null){
                User user = userRepository.findUserById(dto.getUserId());
                if(user==null)throw new ServiceException(" User not present ");
                workshop.setUser(user);
             Workshop work = workshopRepository.save(workshop);
             id = work.getWorkShopId();
            }
        } catch (ValidationException e) {
            throw new ServiceException(e.getMessage());
        }
        return  id;
    }

}
