package com.reparoSpring.Validation;

import com.reparoSpring.dto.user.UserRequestDto;
import com.reparoSpring.exception.ValidationException;
import com.reparoSpring.model.User;
import com.reparoSpring.model.Vehicle;
import com.reparoSpring.model.Workshop;
import com.reparoSpring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    @Autowired
    private UserRepository userRepository;

    private static  final String STRING_REGEX = "^[A-Za-z\\s]+$";
    private static  final String PASS = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,10}$";
    private static  final String ADDRESS_PATTERN = "^[a-zA-Z0-9\\s.,'#\\-]+(\\s[A-Za-z0-9\\-#]+)?$";
    private static final String VEHICLE_NUMBER_PATTERN = "^[A-Z]{2}\\d{2}[A-Z]{2}\\d{4}$";

    public void stringValidation(String str, String name, int n) throws ValidationException {
        // Define a regular expression pattern for validating the string
        Pattern pat = Pattern.compile(STRING_REGEX);

        // Check if the input string is empty, and if so, throw a ValidationException
        if (str.trim().isEmpty()) {
            throw new ValidationException(name + " can't be empty");
        }

        // Check if the length of the input string is greater than the specified maximum length (n),
        // and if so, throw a ValidationException
        if (str.length() > n) {
            throw new ValidationException(name + " can't be more than " + n);
        }

        // Create a Matcher object to check if the input string matches the defined regular expression
        Matcher match = pat.matcher(str);

        // Check if the input string matches the regular expression,
        // and if not, throw a ValidationException
        if (!match.matches()) {
            throw new ValidationException(name + " can only contain alphabetic characters");
        }
    }
    public void numberValidation(long number) throws ValidationException {
        // Convert the long number to a string for validation
        String str = Long.toString(number);

        // Check if the length of the converted string is greater than 10 digits
        if (str.length() > 10) {
            throw new ValidationException("Number should not be more than 10 digits");
        }

    }

    public boolean passWordValidation(String s) throws ValidationException {
        Matcher match;

        // Define a regular expression pattern for password validation (PASS)
        Pattern pt = Pattern.compile(PASS);

        // Create a Matcher object to check if the input string s matches the password pattern
        match = pt.matcher(s);

        // Check if the input string s matches the password pattern,
        // and if not, throw a ValidationException
        if (!match.matches()) {
            throw new ValidationException("Password should be in AlphaNumeric characters. Example: abc123");
        }

        // Return whether the input string s matches the password pattern (true if it matches, false otherwise)
        return match.matches();
    }
    public  boolean addressValidation(String address) throws ValidationException {
        Matcher match;

            Pattern pat = Pattern.compile(ADDRESS_PATTERN);
            match = pat.matcher(address);
            if(!match.matches())throw new ValidationException("Address should not contain  some special characters");

        return match.matches();


    }
    public boolean vehicleNumberValidation(String num ) throws  ValidationException{
        Matcher match;
            Pattern pat = Pattern.compile(VEHICLE_NUMBER_PATTERN);
            match = pat.matcher(num);
        if(!match.matches()) throw new ValidationException("Vehicle number should be Alphanumeric characters");
        return match.matches();
    }
    public boolean isValidLatitude(double latitude) {
        return latitude >= -90.0 && latitude <= 90.0;
    }

    public boolean isValidLongitude(double longitude) {
        return longitude >= -180.0 && longitude <= 180.0;
    }
    public boolean vehicleTypeValidation(int i ){
        return i == 2 || i == 3 || i == 4;
    }
    public void priceValidation(int price) throws ValidationException{
        if(price>9999){
            throw new ValidationException("price can't be more than 4 digits");
        }
        if(price<=0){
            throw new ValidationException("price can't be less than 0 or  0");
        }
    }
    public boolean doesNotContainAlphabets(String input) {
        // Use a regular expression to check if the string contains no alphabetic characters
        return input != null && !input.matches(".*[a-zA-Z].*");
    }
    public boolean vehicleYearValidation(int yr) throws ValidationException {
            String year = Integer.toString(yr);
            if (year.length() != 4)  throw new ValidationException("Year Can't be more or less than 4 digits");
            LocalDate currentDate = LocalDate.now();
            int currentYear = currentDate.getYear();
            if(yr>currentYear) throw new ValidationException("Year Can't be in future");
            else return true;
        }

    public void userCredentialValidation(User user) throws ValidationException {
        // Validate the user's name using stringValidation method
        stringValidation(user.getName(), "name", 15);

        // Validate the user's password using passWordValidation method
        passWordValidation(user.getPassword());

        // Validate the user's number using numberValidation method
        numberValidation(user.getNumber());
    }
    public void loginCredentialValidation(UserRequestDto user) throws ValidationException {
        // Validate the user's password using passWordValidation method
        passWordValidation(user.getPassword());

        // Validate the user's number using numberValidation method
        numberValidation(user.getNumber());
    }


    public void workshopValidation(Workshop work) throws ValidationException{
        stringValidation(work.getWorkShopName(),"workshopName",25);
        stringValidation(work.getCity(),"city",15);
        stringValidation(work.getState(),"state",15);
        stringValidation(work.getCountry(),"country",15);
        addressValidation(work.getAddress());
        if(!vehicleTypeValidation(work.getType()))throw new ValidationException("Invalid Vehicle Type");
        priceValidation(work.getElectricalPrice());
        priceValidation(work.getEnginePrice());
        priceValidation(work.getSuspensionPrice());
        priceValidation(work.getGeneralPrice());
        if(!doesNotContainAlphabets(work.getOpenTime()))throw new ValidationException("openTime shouldn't contain Alphabets");
        if(!doesNotContainAlphabets(work.getCloseTime()))throw new ValidationException("closeTime shouldn't contain Alphabets");
        if(!isValidLatitude(work.getLatitude()))throw new ValidationException("Invalid Latitude");
        if(!isValidLongitude(work.getLongitude()))throw new ValidationException("Invalid Longitude");
    }
    public void vehicleCredentialValidation(Vehicle vehicle) throws ValidationException{
        stringValidation(vehicle.getCompany(), "company",15);
        stringValidation(vehicle.getModel(), "model",15);
        vehicleNumberValidation(vehicle.getVehicleNumber());
        vehicleTypeValidation(vehicle.getType());
        vehicleYearValidation(vehicle.getYear());
    }

}
