package com.upgrad.hotel.bookingservice.services;

import com.upgrad.hotel.bookingservice.dto.BookingInfoDTO;
import com.upgrad.hotel.bookingservice.dto.BookingInfoResponseDTO;

public interface BookingService {

 BookingInfoResponseDTO saveCustomerBookingInfo(BookingInfoDTO bookingInfo);
 BookingInfoResponseDTO updateTransactionIdForBookingId(int bookingId, int transactionId);
}
