package com.reparoSpring.repository;

import com.reparoSpring.model.Workshop;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface WorkshopRepository extends JpaRepository<Workshop,Integer> {
    List<Workshop> getAllWorkshop();
    Workshop findWorkshopById(int id);
    Workshop findWorkshopByUserId(int userId);


}

