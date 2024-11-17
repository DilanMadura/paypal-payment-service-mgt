package com.dmjtech.application.ppsm.service.impl;

import com.dmjtech.application.ppsm.model.dto.SuccessResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author : Dilan Jayaneththi
 * @mailto : ddmdilan@mail.com
 * @created : 6/30/2024, Sunday, 12:15 PM,
 * @project : basic-project
 * @package : com.dmjtech.application.basic_project.service.impl
 **/

@SpringBootTest
class IntegrationServiceImplTest {
    @Autowired
    private IntegrationService integrationService;

    @Test
    @DisplayName("Success/Failure - checkStatus")
    void checkStatus() {
        SuccessResponseDto responseDto = integrationService.checkStatus();
        Assertions.assertEquals("Your request is accepted.", responseDto.getMessage());
    }
}