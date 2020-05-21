package by.kirill.controller.handler;

import by.kirill.controller.handler.exceptions.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = {CarNotFoundException.class, StatusIncorrectException.class
                , StatusNotFoundException.class, StatusNotUpdatebleException.class
                , StatusNotUniqueException.class})
    protected ResponseEntity<Object> handleCustomException(
            Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        String bodyOfResponse = StringToJson(ex.getCause().getMessage());
        headers.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(ex, bodyOfResponse, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    protected ResponseEntity<?> handleIllegalArgument(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        String bodyOfResponse = StringToJson("wrong argument");
        headers.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(ex, bodyOfResponse, headers, HttpStatus.BAD_REQUEST, request);
    }

    private String StringToJson(String str) {
        return "{\"error\": \"" + str + "\"}";
    }
}
