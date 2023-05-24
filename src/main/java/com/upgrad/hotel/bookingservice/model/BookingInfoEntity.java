package com.upgrad.hotel.bookingservice.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "booking")
public class BookingInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int bookingId;

    @Column(nullable = false)
    private LocalDate fromDate;

    @Column(nullable = false)
    private LocalDate toDate;

    @Column(nullable = false)
    private String aadharNumber;

    @Column(nullable = false)
    private int numOfRooms;

    @Column(nullable = false)
    private String roomNumbers;

    @Column(nullable = false)
    private int roomPrice;

    private int transactionId;

    private LocalDate bookedOn;
}
