package com.upgrad.hotel.bookingservice.dto;

import com.upgrad.hotel.bookingservice.utils.PaymentMode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransactionDetailsDTO {

    private String cardNumber;
    private String upiId;

    private int bookingId;

    @NotEmpty
    @NotNull
    @NotBlank
    private PaymentMode paymentMode;

}
