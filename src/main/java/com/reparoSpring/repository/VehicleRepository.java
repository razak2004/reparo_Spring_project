package com.reparoSpring.repository;

import com.reparoSpring.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle , Integer> {

}
