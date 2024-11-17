package com.dmjtech.application.ppsm.adviser;

import com.dmjtech.application.ppsm.exception.BaseException;
import com.dmjtech.application.ppsm.model.dto.ErrorResponseDto;
import com.dmjtech.application.ppsm.util.component.LogMaker;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;

/**
 * @author : Dilan Jayaneththi
 * @mailto : ddmdilan@mail.com
 * @created : 6/30/2024, Sunday, 12:03 AM,
 * @project : basic-project
 * @package : com.dmjtech.application.basic_project.adviser
 **/

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerAdviser {
    private final LogMaker logMaker;

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponseDto> handleException(Exception exception) {
        var errorText = "Error calling southbound";
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponseDto errorResponse = new ErrorResponseDto(errorText);
        logMaker.setLog(this.getClass().getSimpleName(), "handleException", LogMaker.Protocol.REST, errorResponse, httpStatus);
        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        var errorText = new StringBuilder();
        var size = 0;
        for (ObjectError error : exception.getBindingResult().getAllErrors()) {
            errorText.append(error.getDefaultMessage());
            size++;
            if (size < exception.getBindingResult().getAllErrors().size()) {
                errorText.append(" / ");
            }
        }
        var httpStatus = HttpStatus.BAD_REQUEST;
        ErrorResponseDto errorResponse = new ErrorResponseDto(errorText.toString());
        logMaker.setLog(this.getClass().getSimpleName(), "handleMethodArgumentNotValidException", LogMaker.Protocol.REST, errorResponse, httpStatus);
        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<ErrorResponseDto> handleBaseException(BaseException exception) {
        HttpStatus httpStatus = exception.getHttpStatusCode();
        ErrorResponseDto errorResponse = new ErrorResponseDto(exception.getErrorMessage());
        logMaker.setLog(this.getClass().getSimpleName(), "handleBaseException", LogMaker.Protocol.REST, errorResponse, httpStatus);
        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    @ExceptionHandler(value = InvalidFormatException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidFormatException(InvalidFormatException exception) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ErrorResponseDto errorResponse = new ErrorResponseDto(exception.getMessage());
        logMaker.setLog(this.getClass().getSimpleName(), "handleInvalidFormatException", LogMaker.Protocol.REST, errorResponse, httpStatus);
        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<ErrorResponseDto> handleAccessDeniedException(AccessDeniedException exception) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ErrorResponseDto errorResponse = new ErrorResponseDto(exception.getMessage());
        logMaker.setLog(this.getClass().getSimpleName(), "handleAccessDeniedException", LogMaker.Protocol.REST, errorResponse, httpStatus);
        return new ResponseEntity<>(errorResponse, httpStatus);
    }
}
