package com.upgrad.hotel.bookingservice.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingInfoResponseDTO {

    private int bookingId;

    private LocalDate fromDate;

    private LocalDate toDate;

    private String aadharNumber;

    private String roomNumbers;

    private int roomPrice;

    private int transactionId;

    private LocalDate bookedOn;

}
