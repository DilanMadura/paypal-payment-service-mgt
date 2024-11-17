package com.dmjtech.application.ppsm.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author : Dilan Jayaneththi
 * @mailto : ddmdilan@mail.com
 * @created : 6/29/2024, Saturday, 11:33 PM,
 * @project : basic-project
 * @package : com.dmjtech.application.basic_project.exception
 **/

@Getter
@AllArgsConstructor
public class BaseException extends Exception {
    private final String errorMessage;
    private final HttpStatus httpStatusCode;
}
