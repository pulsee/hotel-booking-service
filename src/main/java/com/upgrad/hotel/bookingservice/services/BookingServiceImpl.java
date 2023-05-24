package com.upgrad.hotel.bookingservice.services;

import com.upgrad.hotel.bookingservice.dto.BookingInfoDTO;
import com.upgrad.hotel.bookingservice.dto.BookingInfoResponseDTO;
import com.upgrad.hotel.bookingservice.model.BookingInfoEntity;
import com.upgrad.hotel.bookingservice.repositories.BookingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public BookingInfoResponseDTO saveCustomerBookingInfo(BookingInfoDTO bookingInfo) {

        BookingInfoEntity bookingInfoEntity = modelMapper.map(bookingInfo, BookingInfoEntity.class);

        int numOfDays = (int) getDayDiff(bookingInfo.getFromDate(), bookingInfo.getToDate());
        System.out.println("num of days = " + numOfDays);

        int roomPrice = getRoomPrice(bookingInfo.getNumOfRooms(), numOfDays);
        System.out.println("roomPrice = " + roomPrice);

        bookingInfoEntity.setRoomPrice(roomPrice);

        String roomsString = String.join(",", getRandomNumbers(bookingInfo.getNumOfRooms()));
        System.out.println("roomString = " + roomsString);

        bookingInfoEntity.setRoomNumbers(roomsString);
        bookingInfoEntity.setBookedOn(LocalDate.now());

        BookingInfoEntity savedBookingInfo = bookingRepository.save(bookingInfoEntity);

        System.out.println(savedBookingInfo);

        return modelMapper.map(savedBookingInfo, BookingInfoResponseDTO.class);
    }

    @Override
    public BookingInfoResponseDTO updateTransactionIdForBookingId(int bookingId, int transactionId) {
        BookingInfoEntity bookingInfoEntity = bookingRepository.findById(bookingId).orElse(null);
        if (bookingInfoEntity!=null)
        {
            bookingInfoEntity.setTransactionId(transactionId);
            BookingInfoEntity savedBookingInfo = bookingRepository.save(bookingInfoEntity);
            return modelMapper.map(savedBookingInfo, BookingInfoResponseDTO.class);
        }
        return null;
    }

    private long getDayDiff(LocalDate d1, LocalDate d2) {
        return ChronoUnit.DAYS.between(d1, d2);
    }

    private int getRoomPrice(int numOfRooms, int numOfDays) {
        return 1000 * numOfRooms * numOfDays;
    }

    private ArrayList<String> getRandomNumbers(int count) {
        Random rand = new Random();
        int upperBound = 100;
        ArrayList<String> numberList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            numberList.add(String.valueOf(rand.nextInt(upperBound)));
        }

        return numberList;
    }
}
