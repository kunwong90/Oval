package com.learn.nested.entity;

import com.learn.utils.ValidationUtils;

import java.util.ArrayList;
import java.util.List;

public class PassengeCheckStatusInputTest {

    public static void main(String[] args) {

        PassengeCheckStatusInput passengeCheckStatusInput = new PassengeCheckStatusInput();
        List<Passenger> passengers = new ArrayList<>();
        Passenger passenger = new Passenger();
        passenger.setPassengerName("11");
        passenger.setPassportTypeId(" 嗯嗯 ");
        passengers.add(passenger);
        passengeCheckStatusInput.setPassengers(passengers);
        ValidationUtils.validate(passengeCheckStatusInput);
    }
}
