package com.reparoSpring.datamapper;

import com.reparoSpring.dto.workshop.WorkshopRequestDto;
import com.reparoSpring.dto.workshop.WorkshopResponseDto;
import com.reparoSpring.model.Workshop;

public class WorkshopMapper {
    private final UserMapper map =  new UserMapper();
    public Workshop mapRequestToWorkshop(WorkshopRequestDto data){
        Workshop workshop = new Workshop();
        workshop.setWorkShopName(data.getWorkshopName());
        workshop.setImage(data.getImage());
        workshop.setCity(data.getCity());
        workshop.setState(data.getState());
        workshop.setCountry(data.getCountry());
        workshop.setAddress(data.getAddress());
        workshop.setLatitude(data.getLatitude());
        workshop.setLongitude(data.getLongitude());
        workshop.setType(data.getType());
        workshop.setOpenTime(data.getOpenTime());
        workshop.setCloseTime(data.getCloseTime());
        workshop.setGeneralPrice(data.getGeneralPrice());
        workshop.setElectricalPrice(data.getElectricalPrice());
        workshop.setEnginePrice(data.getEnginePrice());
        workshop.setSuspensionPrice(data.getSuspensionPrice());
        return workshop;
    }
    public WorkshopResponseDto mapWorkshopToResponse(Workshop workshop){
        WorkshopResponseDto workshopResponseDto =  new WorkshopResponseDto();
        workshopResponseDto.setWorkshopId(workshop.getWorkShopId());
        workshopResponseDto.setUserId(workshop.getUser().getId());
        workshopResponseDto.setUser(map.mapUserToResponse(workshop.getUser()));
        workshopResponseDto.setWorkshopName(workshop.getWorkShopName());
        workshopResponseDto.setImage(workshop.getImage());
        workshopResponseDto.setCity(workshop.getCity());
        workshopResponseDto.setState(workshop.getState());
        workshopResponseDto.setCountry(workshop.getCountry());
        workshopResponseDto.setAddress(workshop.getAddress());
        workshopResponseDto.setType(workshop.getType());
        workshopResponseDto.setOpenTime(workshop.getOpenTime());
        workshopResponseDto.setCloseTime(workshop.getCloseTime());
        workshopResponseDto.setGeneralPrice(workshop.getGeneralPrice());
        workshopResponseDto.setElectricalPrice(workshop.getElectricalPrice());
        workshopResponseDto.setEnginePrice(workshop.getEnginePrice());
        workshopResponseDto.setSuspensionPrice(workshop.getSuspensionPrice());
        return workshopResponseDto;
    }

}
