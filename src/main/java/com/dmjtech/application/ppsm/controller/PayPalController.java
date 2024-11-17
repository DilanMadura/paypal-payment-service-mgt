package com.dmjtech.application.ppsm.controller;

import com.dmjtech.application.ppsm.exception.BaseException;
import com.dmjtech.application.ppsm.model.dto.ErrorResponseDto;
import com.dmjtech.application.ppsm.model.dto.PaymentResponseDto;
import com.dmjtech.application.ppsm.model.dto.SuccessResponseDto;
import com.dmjtech.application.ppsm.service.PaypalService;
import com.dmjtech.application.ppsm.util.component.LogMaker;
import com.paypal.api.payments.Payment;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Dilan Jayaneththi
 * @mailto : ddmdilan@mail.com
 * @created : 11/17/2024, Sunday, 12:10 AM,
 * @project : payment-service-mgt
 * @package : com.dmjtech.application.controller
 **/
@RestController
@RequestMapping("${application.base-url.context}/paypal/payment")
@RequiredArgsConstructor
public class PayPalController {
    private final LogMaker logMaker;
    private final PaypalService payPalPaymentService;

    @Operation(summary = "create payment", description = "create payment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = PaymentResponseDto.class))),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))})
    @PostMapping("/create")
    public ResponseEntity<PaymentResponseDto> createPayment(@RequestParam String total, HttpServletRequest request) throws BaseException {
        logMaker.setLog(this.getClass().getSimpleName(), "createPayment", LogMaker.Protocol.REST, "", request);
        PaymentResponseDto response = payPalPaymentService.createPayment(total, "USD", "paypal", "sale", "Payment description", request);
        logMaker.setLog(this.getClass().getSimpleName(), "createPayment", LogMaker.Protocol.REST, response, HttpStatus.OK);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "success payment", description = "success payment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = SuccessResponseDto.class))),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))})
    @GetMapping("/success")
    public ResponseEntity<SuccessResponseDto> successPayment(@RequestParam String paymentId, @RequestParam String payerId, HttpServletRequest request) throws BaseException {
        Payment payment = payPalPaymentService.executePayment(paymentId, payerId);
        logMaker.setLog(this.getClass().getSimpleName(), "success", LogMaker.Protocol.REST, "", request);
        SuccessResponseDto response = new SuccessResponseDto("Payment completed successfully! Payment ID: " + payment.getId());
        logMaker.setLog(this.getClass().getSimpleName(), "success", LogMaker.Protocol.REST, response, HttpStatus.OK);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "cancel payment", description = "cancel payment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = SuccessResponseDto.class))),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))})

    @GetMapping("/cancel")
    public ResponseEntity<SuccessResponseDto> cancelPayment(HttpServletRequest request) {
        logMaker.setLog(this.getClass().getSimpleName(), "cancelPayment", LogMaker.Protocol.REST, "", request);
        SuccessResponseDto response = new SuccessResponseDto("Payment cancelled.");
        logMaker.setLog(this.getClass().getSimpleName(), "cancelPayment", LogMaker.Protocol.REST, response, HttpStatus.OK);
        return ResponseEntity.ok().body(response);
    }
}