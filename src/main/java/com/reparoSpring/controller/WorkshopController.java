package com.reparoSpring.controller;
import com.reparoSpring.dto.workshop.WorkshopRequestDto;
import com.reparoSpring.dto.workshop.WorkshopResponseDto;
import com.reparoSpring.exception.ServiceException;
import com.reparoSpring.service.WorkshopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workshop")
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class WorkshopController {
@Autowired
private  WorkshopService workshopService;


    @PostMapping("/createWorkshop")
    public ResponseEntity<String>createWorkShop(@RequestBody WorkshopRequestDto request,@RequestParam int userId){
        request.setUserId(userId);
        try {
          int id =  workshopService.createWorkshop(request);
            return ResponseEntity.ok(Integer.toString(id));

        } catch (ServiceException e) {
            return ResponseEntity.ok(e.getMessage());

        }

    }
    @GetMapping("/getAllWorkshops")
    public ResponseEntity<List<WorkshopResponseDto>> getAllWorkshops(@RequestParam int id){
        try {
         List<WorkshopResponseDto> workshops  =  workshopService.getAllWorkshops(id);
         return ResponseEntity.ok(workshops);
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }







}
