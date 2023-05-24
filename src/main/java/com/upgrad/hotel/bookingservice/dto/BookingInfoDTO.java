package com.upgrad.hotel.bookingservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class BookingInfoDTO {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate fromDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate toDate;

    @NotBlank(message = "aadharNumber can't be empty")
    private String aadharNumber;

    private int numOfRooms;
}
