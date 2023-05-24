package com.upgrad.hotel.bookingservice.exceptions;

import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BookingExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        HttpInputMessage httpInputMessage = ex.getHttpInputMessage();
        System.out.println(httpInputMessage);

        ErrorResponse errorResponse = new ErrorResponse();

        if (ex.getMessage().contains("not one of the values accepted for Enum class: [CARD, UPI]")) {

            errorResponse.setMessage("Invalid mode of payment");
            errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(errorResponse);
        }
        return super.handleHttpMessageNotReadable(ex, headers, status, request);
    }
}
