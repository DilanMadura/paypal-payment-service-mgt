package com.dmjtech.application.ppsm.service.impl;

import com.dmjtech.application.ppsm.exception.BaseException;
import com.dmjtech.application.ppsm.model.dto.PaymentResponseDto;
import com.dmjtech.application.ppsm.service.PaypalService;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Dilan Jayaneththi
 * @mailto : ddmdilan@mail.com
 * @created : 11/16/2024, Saturday, 11:33 PM,
 * @project : payment-service-mgt
 * @package : com.dmjtech.application.service.impl
 **/

@Service
@RequiredArgsConstructor
public class PaypalServiceImpl implements PaypalService {
    private final APIContext apiContext;

    @Override
    public PaymentResponseDto createPayment(String total, String currency, String method, String intent, String description, HttpServletRequest request) throws BaseException {

        int lastSlashIndex = request.getRequestURI().lastIndexOf('/');
        String contextHost = request.getRequestURI().substring(0, lastSlashIndex);

        String cancelUrl = contextHost + "/cancel";
        String successUrl = contextHost + "/success";

        //String totalAmount = String.format(Locale.forLanguageTag(currency), "%2.f", total);
        Amount amount = new Amount(currency, total);

        Transaction transaction = new Transaction();
        transaction.setDescription(description);
        transaction.setAmount(amount);

        Payer payer = new Payer();
        payer.setPaymentMethod(method);

        Payment payment = new Payment();
        payment.setIntent(intent);
        payment.setPayer(payer);
        payment.setTransactions(List.of(transaction));

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);
        payment.setRedirectUrls(redirectUrls);

        try {
            Payment createdPayment = payment.create(apiContext);
            String approvalUrl = createdPayment.getLinks().stream()
                    .filter(link -> link.getRel().equals("approval_url"))
                    .findFirst()
                    .map(Links::getHref)
                    .orElseThrow(() -> new RuntimeException("Approval URL not found"));

            return new PaymentResponseDto(total, approvalUrl, createdPayment.getId(), description, currency, createdPayment.getState());
        } catch (PayPalRESTException exception) {
            throw new BaseException(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public Payment executePayment(String paymentId, String payerId) throws BaseException {
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);

        try {
            return payment.execute(apiContext, paymentExecution);
        } catch (PayPalRESTException exception) {
            throw new BaseException(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
