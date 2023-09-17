package com.reparo.datamapper;

import com.reparo.dto.vehicle.VehicleRequestDto;
import com.reparo.model.Vehicle;

public class VehicleMapper {

    public Vehicle mapRequestToVehicle(VehicleRequestDto dto){
        return new Vehicle(dto.getVehicleType(), dto.getVehicleNumber(), dto.getVehicleYear(), dto.getVehicleCompany(), dto.getModel());

    }
}
