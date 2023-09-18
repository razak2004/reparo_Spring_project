package com.reparo.controller;

import com.reparo.dto.booking.BookingRequestDto;
import com.reparo.dto.workshop.WorkshopDistanceResponseDto;
import com.reparo.exception.ServiceException;
import com.reparo.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/createBooking")
    public ResponseEntity<String> createBooking(@RequestBody BookingRequestDto request){
        try {
          int id = bookingService.createBooking(request);
          return ResponseEntity.ok(Integer.toString(id));
        } catch (ServiceException e) {
            return ResponseEntity.ok(e.getMessage());
        }


    }
    @GetMapping("/nearWorkshops")
    public ResponseEntity<List<WorkshopDistanceResponseDto>> getNearByWorkshop(@RequestParam("bookingId")int bookingId){
        try {
            return ResponseEntity.ok(bookingService.getBookingNearWorkshops(bookingId));
        } catch (ServiceException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
