package com.upgrad.hotel.bookingservice.controllers;

import com.upgrad.hotel.bookingservice.dto.BookingInfoDTO;
import com.upgrad.hotel.bookingservice.dto.BookingInfoResponseDTO;
import com.upgrad.hotel.bookingservice.dto.TransactionDetailsDTO;
import com.upgrad.hotel.bookingservice.services.BookingService;
import com.upgrad.hotel.bookingservice.services.BookingServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@RestController
@RequestMapping("/hotel")
public class BookingController {

    @Autowired
    BookingServiceImpl bookingService;

    @Autowired
    RestTemplate restTemplate;

    @Value("${url.transactionRequest}")
    String transactionRequestUrl;

    @Value("${url.transactionDetails}")
    String transactionDetailsUrl;

    @PostMapping("/booking")
    ResponseEntity<?> requestBookingPrice(@Valid @RequestBody  BookingInfoDTO bookingInfoDTO){
        BookingInfoResponseDTO bookingInfoResponseDTO = bookingService.saveCustomerBookingInfo(bookingInfoDTO);
        return new ResponseEntity<>(bookingInfoResponseDTO, HttpStatus.CREATED);
    }
    @PostMapping("/booking/{bookingId}/transaction")
    ResponseEntity<?> requestBookingTransaction(@Valid @RequestBody TransactionDetailsDTO transactionDetailsDTO, @PathVariable int bookingId){

        ResponseEntity<String> transactionResponse = restTemplate.postForEntity(transactionRequestUrl,transactionDetailsDTO, String.class, bookingId);

        if (transactionResponse.getStatusCode().is4xxClientError()){
            return ResponseEntity.badRequest().body(transactionResponse.getBody());
        }
        int transactionId = Integer.parseInt(transactionResponse.getBody());
        if (transactionId>0)
        {
            BookingInfoResponseDTO bookingInfoResponseDTO = bookingService.updateTransactionIdForBookingId(bookingId,transactionId);
            return new ResponseEntity<>(bookingInfoResponseDTO, HttpStatus.CREATED);

        }

        return ResponseEntity.badRequest().body("transactionId or bookingId does not exist");
        //return new ResponseEntity<>(bookingInfoResponseDTO, HttpStatus.CREATED);
    }
}
