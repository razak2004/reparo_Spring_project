package com.reparoSpring.validation;

import com.reparoSpring.Validation.Validation;
import com.reparoSpring.exception.ValidationException;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class ValidationTest {

    private final Validation validate = new Validation() ;


    @Test
    void stringValidationTest(){
        try {
            validate.stringValidation("Abdul razak","name",15);
        } catch (ValidationException e) {
            fail();
            throw new RuntimeException(e);
        }

    }
    @Test
    void stringValidationPatternFailTest(){
        ValidationException exception = assertThrows(ValidationException.class, () -> validate.stringValidation("Abdul 3a2ak","name",15));

        assertEquals("name can only contain alphabetic characters", exception.getMessage());

    }
    @Test
    void stringValidationLengthFailTest(){
        ValidationException exception = assertThrows(ValidationException.class, () -> validate.stringValidation("Abdul razak","name",7));

        assertEquals("name can't be more than 7", exception.getMessage());

    }
    @Test
    void numberValidationTest(){
        try {
            validate.numberValidation(8124311602L);
        } catch (ValidationException e) {
            fail();
            throw new RuntimeException(e);
        }

    }
    @Test
    void numberValidationFailTest(){
        ValidationException exception = assertThrows(ValidationException.class, () -> validate.numberValidation(81243116023L));

        assertEquals("Number should not be more than 10 digits", exception.getMessage());

    }
    @Test
    void testPasswordValidation(){

        try {
           assertTrue(validate.passWordValidation("abd123"));
        }catch(ValidationException e){
            fail();
            throw new RuntimeException(e);
        }

    }
    @Test
    void testInvalidPasswordValidation(){
        ValidationException exception = assertThrows(ValidationException.class, () -> validate.passWordValidation("123456"));

        assertEquals("Password should be in AlphaNumeric characters. Example: abc123", exception.getMessage());

    }
    @Test
    void addressValidation(){
        String address = "123  Main Street";
        try {
            assertTrue(validate.addressValidation(address));

        }catch(ValidationException e){
           fail();
        }

    }
    @Test
    void latitudeValidation(){
       assertTrue(validate.isValidLatitude(45.678));
    }
    @Test
    void longitudeValidation(){
        assertTrue(validate.isValidLongitude(180.0));
    }
    @Test
    void latitudeInValidation(){
        assertFalse(validate.isValidLatitude(-91.0));
    }
    @Test
    void longitudeInValidation(){
        assertFalse(validate.isValidLongitude(200.0));
    }
    @Test
    void doesNotAlphabet(){
        assertTrue(validate.doesNotContainAlphabets("12345"));
    }
    @Test
    void doesAlphabet(){
        assertFalse(validate.doesNotContainAlphabets("Hello123"));
    }


}
