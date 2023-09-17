package com.reparo.service;

import com.reparo.datamapper.VehicleMapper;
import com.reparo.dto.vehicle.VehicleRequestDto;
import com.reparo.exception.ServiceException;
import com.reparo.model.User;
import com.reparo.model.Vehicle;
import com.reparo.repository.UserRepository;
import com.reparo.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService service;
    private final VehicleMapper map = new VehicleMapper();
    public int addVehicle(VehicleRequestDto dto) throws ServiceException{
        try {
            service.isUserExist(dto.getUserId());
            User user =  userRepository.findUserById(dto.getUserId());
            Vehicle vehicle = map.mapRequestToVehicle(dto);
            vehicle.setUser(user);
            Vehicle registerVehicle  = vehicleRepository.save(vehicle);
            return registerVehicle.getVehicleId();
        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
