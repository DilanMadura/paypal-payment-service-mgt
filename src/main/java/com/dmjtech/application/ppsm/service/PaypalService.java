package com.dmjtech.application.ppsm.service;

import com.dmjtech.application.ppsm.exception.BaseException;
import com.dmjtech.application.ppsm.model.dto.PaymentResponseDto;
import com.paypal.api.payments.Payment;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author : Dilan Jayaneththi
 * @mailto : ddmdilan@mail.com
 * @created : 11/16/2024, Saturday, 11:33 PM,
 * @project : payment-service-mgt
 * @package : com.dmjtech.application.service
 **/
public interface PaypalService {
    PaymentResponseDto createPayment(String total, String currency, String method, String intent, String description, HttpServletRequest request) throws BaseException;

    Payment executePayment(String paymentId, String payerId) throws BaseException;
}
