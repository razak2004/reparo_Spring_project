package com.reparo.service;

import com.reparo.dto.booking.BookingAcceptRequestDto;
import com.reparo.dto.booking.BookingRequestDto;
import com.reparo.exception.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class BookingServiceTest {
    @Autowired
    private BookingService service;

    @Test
    void createBookingTest(){
        try {
            BookingRequestDto request =  new BookingRequestDto();
            request.setBookedAddress("no 123 cross street");
            request.setBookedCity("chennai");
            request.setBookedState("tamilNadu");
            request.setBookedCountry("india");
            request.setBookedLatitude(45.678);
            request.setBookedLongitude(180.0);
            request.setBookedVehicleId(2);
            request.setProblem("puncture");
            if(service!= null){
                assertNotEquals(0,service.createBooking(request));
            }
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void existsBookingIdTest(){
        try {
            assertTrue(service.isBookingExists(3))  ;
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void acceptBookingTest(){
        BookingAcceptRequestDto booking =  new BookingAcceptRequestDto();
        booking.setBookingId(2);
        booking.setWorkshopId(2);
        try {
            assertTrue(service.acceptBooking(booking));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}
