package com.reparo.service;
import com.reparo.datamapper.BookingMapper;
import com.reparo.dto.booking.BookingAcceptRequestDto;
import com.reparo.dto.booking.BookingRequestDto;
import com.reparo.dto.booking.BookingResponseDto;
import com.reparo.dto.workshop.WorkshopDistanceResponseDto;
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

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

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
    public List<WorkshopDistanceResponseDto> getBookingNearWorkshops(int id)throws ServiceException{
        try {
            isBookingExists(id);
            List<WorkshopDistanceResponseDto> responseDto =  new ArrayList<>();

            if(bookingRepository!=null){
                Booking book = bookingRepository.findByBookingId(id);
                responseDto = workshopService.findWorkshopsNearByArea(book.getLatitude(),book.getLongitude(),book.getBookingCity());
            }
            return responseDto;

        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public List<BookingResponseDto> getUnAcceptedBooking(double lat,double lon,String city) throws ServiceException{
        try {
            List<BookingResponseDto> response =  new ArrayList<>();
            validate.stringValidation(city,"city",25);
            String[]arr =  city.toLowerCase().split(" ");
            if(bookingRepository!=null){
                List<Booking> bookings =  bookingRepository.findByBookingCity(arr[0]);
                if(bookings.isEmpty()) throw new ServiceException("No Bookings Available");
                for (Booking book:bookings) {
                    if(!book.isAcceptStatus()){
                        BookingResponseDto responseDto =  map.mapUnAcceptBookingToResponse(book);
                        double dis  = workshopService.calculateDistance(lat,lon,book.getLatitude(),book.getLongitude());
                        responseDto.setDistance(dis);
                        response.add(responseDto);
                    }

                }

            }
            return response;

        } catch (ValidationException e) {
            throw new ServiceException(e.getMessage());
        }

    }
}
