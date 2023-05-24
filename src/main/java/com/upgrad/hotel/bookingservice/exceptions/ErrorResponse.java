package com.upgrad.hotel.bookingservice.exceptions;

import lombok.Data;

import java.util.List;

@Data
public class ErrorResponse {
    String message;
    int statusCode;
}
