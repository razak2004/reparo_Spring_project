package com.reparo.service;
import com.reparo.datamapper.BookingMapper;
import com.reparo.dto.booking.BookingAcceptRequestDto;
import com.reparo.dto.booking.BookingRequestDto;
import com.reparo.exception.ServiceException;
import com.reparo.exception.ValidationException;
import com.reparo.model.Booking;
import com.reparo.model.Vehicle;
import com.reparo.model.Workshop;
import com.reparo.repository.BookingRepository;
import com.reparo.repository.VehicleRepository;
import com.reparo.repository.WorkshopRepository;
import com.reparo.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.text.SimpleDateFormat;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private WorkshopService workshopService;
    @Autowired
    private WorkshopRepository workshopRepository;
    private final BookingMapper map = new BookingMapper();
    private final Validation validate =  new Validation();


    public boolean isBookingExists(int id) throws  ServiceException{
        boolean exist = false;
        if(bookingRepository!=null){
            exist =  bookingRepository.existsById(id);
            if(!exist) throw  new ServiceException("Booking Not present");
        }
        return exist;
    }


    public int createBooking(BookingRequestDto request)throws ServiceException{
        try {
            int id = 0 ;
            if(bookingRepository!=null && vehicleService!=null && vehicleRepository!=null){
                vehicleService.isVehicleExist(request.getBookedVehicleId());
                Vehicle  vehicle = vehicleRepository.findByVehicleId(request.getBookedVehicleId());
                Booking newBooking = map.mapRequestToBooking(request);
                Date current= new Date();
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat time = new SimpleDateFormat("HH:mm");
                newBooking.setBookingDate(date.format(current));
                newBooking.setBookingTime(time.format(current));
                validate.bookingCredentialValidation(newBooking);
                newBooking.setVehicle(vehicle);
                newBooking.setRequestStatus(true);
                newBooking.setLive(true);
                Booking book  = bookingRepository.save(newBooking);
                id= book.getBookingId();
            }
            return id;
        } catch (ServiceException | ValidationException e) {
            throw new ServiceException(e.getMessage());
        }

    }
    public boolean acceptBooking(BookingAcceptRequestDto accept) throws ServiceException{
        try {
            Booking booking =  new Booking();
            if(bookingRepository!=null && workshopRepository!=null){
                workshopService.isWorkshopExist(accept.getWorkshopId());
                Workshop workshop = workshopRepository.findById(accept.getWorkshopId());
                isBookingExists(accept.getBookingId());
                Booking book = bookingRepository.findByBookingId(accept.getBookingId());
                book.setAcceptStatus(true);
                book.setWorkshop(workshop);
                booking = bookingRepository.save(book);
            }

          return  booking.isAcceptStatus();

        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
