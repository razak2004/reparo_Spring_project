package com.reparoSpring.datamapper;

import com.reparoSpring.dto.vehicle.VehicleRequestDto;
import com.reparoSpring.model.Vehicle;

public class VehicleMapper {

    public Vehicle mapRequestToVehicle(VehicleRequestDto dto){
        return new Vehicle(dto.getType(), dto.getVehicleNumber(), dto.getYear(), dto.getVehicleNumber(), dto.getModel());

    }
}
