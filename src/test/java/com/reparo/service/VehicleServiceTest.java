package com.reparo.service;

import com.reparo.dto.vehicle.VehicleRequestDto;
import com.reparo.exception.ServiceException;
import com.reparo.repository.VehicleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class VehicleServiceTest {
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired

    private  VehicleService  vehicleService;
    @Test
    void createVehicleTest(){
        try {
            if(vehicleService!=null){
                VehicleRequestDto dto = new VehicleRequestDto();
                dto.setCompany("hero");
                dto.setModel("splendor");
                dto.setVehicleNumber("TN09AB1234");
                dto.setType(2);
                dto.setUserId(953);
                dto.setYear(2022);
                Assertions.assertNotNull( vehicleService.addVehicle(dto));

            }

        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }



}
