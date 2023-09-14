package com.reparoSpring.controller;


import com.reparoSpring.dto.workshop.WorkshopRequestDto;
import com.reparoSpring.exception.ServiceException;
import com.reparoSpring.service.WorkshopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/workshop")
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class WorkshopController {

    private final WorkshopService workshopService =  new WorkshopService();

//workshop map 
    @PostMapping("/createWorkshop")
    public ResponseEntity<String>createWorkShop(@RequestBody WorkshopRequestDto request){
        try {
          int id =  workshopService.createWorkshop(request);
            return ResponseEntity.ok(Integer.toString(id));

        } catch (ServiceException e) {
            return ResponseEntity.ok(e.getMessage());

        }

    }







}
