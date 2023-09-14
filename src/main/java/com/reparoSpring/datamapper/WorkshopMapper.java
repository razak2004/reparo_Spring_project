package com.reparoSpring.datamapper;

import com.reparoSpring.dto.workshop.WorkshopRequestDto;
import com.reparoSpring.model.Workshop;

public class WorkshopMapper {
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

}
