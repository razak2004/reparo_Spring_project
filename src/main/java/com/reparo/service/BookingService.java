package com.reparo.service;
import com.reparo.datamapper.BookingMapper;
import com.reparo.dto.booking.BookingRequestDto;
import com.reparo.exception.ServiceException;
import com.reparo.exception.ValidationException;
import com.reparo.model.Booking;
import com.reparo.model.Vehicle;
import com.reparo.repository.BookingRepository;
import com.reparo.repository.VehicleRepository;
import com.reparo.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private VehicleRepository vehicleRepository;
    private final BookingMapper map = new BookingMapper();
    private final Validation validate =  new Validation();


    public int createBooking(BookingRequestDto request){
        try {
            int id = 0 ;
            if(bookingRepository!=null && vehicleService!=null && vehicleRepository!=null){
                vehicleService.isVehicleExist(request.getBookedVehicleId());
                Vehicle  vehicle = vehicleRepository.findByVehicleId(request.getBookedVehicleId());
                Booking newBooking = map.mapRequestToBooking(request);
                validate.bookingCredentialValidation(newBooking);
                newBooking.setVehicle(vehicle);
                Booking book  = bookingRepository.save(newBooking);
                id= book.getBookingId();
            }
            return id;
        } catch (ServiceException | ValidationException e) {
            throw new RuntimeException(e);
        }

    }
}
