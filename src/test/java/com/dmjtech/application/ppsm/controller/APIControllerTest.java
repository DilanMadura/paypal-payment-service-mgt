package com.dmjtech.application.ppsm.controller;

import com.dmjtech.application.ppsm.model.dto.SuccessResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author : Dilan Jayaneththi
 * @mailto : ddmdilan@mail.com
 * @created : 6/30/2024, Sunday, 1:03 AM,
 * @project : basic-project
 * @package : com.dmjtech.application.basic_project.controller
 **/

@SpringBootTest
@AutoConfigureMockMvc
class APIControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private IntegrationService integrationService;

    @Test
    void checkStatus() throws Exception {
        Mockito.doReturn(new SuccessResponseDto("Your request is accepted.")).when(integrationService).checkStatus();

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/basic-project/1.0.0/check-status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .servletPath("/basic-project/1.0.0/check-status"))
                .andExpect(status().isOk()).andReturn();

        SuccessResponseDto response = objectMapper.readValue(result.getResponse().getContentAsString(), SuccessResponseDto.class);
        Assertions.assertEquals("Your request is accepted.", response.getMessage());
    }
}