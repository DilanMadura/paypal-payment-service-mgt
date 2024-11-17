package com.dmjtech.application.ppsm.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Dilan Jayaneththi
 * @mailto : ddmdilan@mail.com
 * @created : 6/29/2024, Saturday, 11:34 PM,
 * @project : basic-project
 * @package : com.dmjtech.application.basic_project.model.dto
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDto {
    private String message;
}
