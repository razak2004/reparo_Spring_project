package com.reparo.repository;

import com.reparo.model.Vehicle;
import jakarta.persistence.Column;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle , Integer> {
    Vehicle findByVehicleId(int vehicleId);


}
