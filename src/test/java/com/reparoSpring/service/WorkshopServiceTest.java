package com.reparoSpring.service;

import com.reparoSpring.dto.workshop.WorkshopRequestDto;
import com.reparoSpring.exception.ServiceException;
import com.reparoSpring.repository.WorkshopRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WorkshopServiceTest {
    @Autowired
    private WorkshopRepository workshopRepository;
    @Autowired
    private WorkshopService workshopService;

    @Test
    void saveWorkshop(){
        WorkshopRequestDto requestDto =  new WorkshopRequestDto();
        requestDto.setUserId(702);
        requestDto.setCity("chennai");
        requestDto.setState("tamilNadu");
        requestDto.setCountry("India");
        requestDto.setWorkshopName("automobile");
        requestDto.setAddress("123 cross street");
        requestDto.setType(2);
        requestDto.setLatitude(45.678);
        requestDto.setLongitude(180.0);
        requestDto.setElectricalPrice(3000);
        requestDto.setEnginePrice(4000);
        requestDto.setSuspensionPrice(300);
        requestDto.setGeneralPrice(500);
        requestDto.setOpenTime("23:32");
        requestDto.setCloseTime("20:21");
        try {
         int id =   workshopService.createWorkshop(requestDto);
            Assertions.assertNotNull(workshopRepository.findById(id));
            System.out.println(id);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }


    }


}
