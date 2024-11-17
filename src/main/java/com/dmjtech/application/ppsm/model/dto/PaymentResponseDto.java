package com.dmjtech.application.ppsm.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Dilan Jayaneththi
 * @mailto : ddmdilan@mail.com
 * @created : 11/17/2024, Sunday, 10:27 AM,
 * @project : payment-service-mgt
 * @package : com.dmjtech.application.model.dto
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponseDto {
    private String amount;
    private String approvalUrl;
    private String paymentId;
    private String description;
    private String currency;
    private String state;
}
