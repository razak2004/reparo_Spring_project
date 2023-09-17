package com.reparo.datamapper;

import com.reparo.dto.vehicle.VehicleRequestDto;
import com.reparo.model.Vehicle;

public class VehicleMapper {

    public Vehicle mapRequestToVehicle(VehicleRequestDto dto){
        return new Vehicle(dto.getType(), dto.getVehicleNumber(), dto.getYear(), dto.getVehicleNumber(), dto.getModel());

    }
}
