package com.example.cinema_booking.exception;

public class CustomerNotFoundException extends Throwable {
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
