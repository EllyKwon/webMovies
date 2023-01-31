package com.webMovies.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    protected void handleException(Exception e){
        log.info("##### ERROR : {}", e.getMessage());
    }

}
