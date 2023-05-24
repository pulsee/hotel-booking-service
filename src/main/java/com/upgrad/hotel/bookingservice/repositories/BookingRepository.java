package com.upgrad.hotel.bookingservice.repositories;


import com.upgrad.hotel.bookingservice.model.BookingInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<BookingInfoEntity, Integer> {


}
