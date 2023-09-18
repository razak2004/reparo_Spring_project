package com.reparo.datamapper;

import com.reparo.dto.booking.BookingRequestDto;
import com.reparo.model.Booking;

public class BookingMapper {

    public Booking mapRequestToBooking(BookingRequestDto requestDto){
        Booking booking = new Booking();
        booking.setBookingCity(requestDto.getBookedCity());
        booking.setBookingCountry(requestDto.getBookedCountry());
        booking.setBookingState(requestDto.getBookedState());
        booking.setBookingAddress(requestDto.getBookedAddress());
        booking.setLatitude(requestDto.getBookedLatitude());
        booking.setLongitude(requestDto.getBookedLongitude());
        booking.setProblem(requestDto.getProblem());
        return booking;
    }
}
