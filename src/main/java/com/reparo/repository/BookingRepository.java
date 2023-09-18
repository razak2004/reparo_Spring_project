package com.reparo.repository;

import com.reparo.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Integer> {
    Booking findByBookingId(int bookingId);
    List<Booking> findByBookingCity(String city);

}
