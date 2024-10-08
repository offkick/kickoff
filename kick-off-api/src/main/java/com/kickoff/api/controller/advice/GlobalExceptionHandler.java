package com.kickoff.api.controller.advice;

import com.kickoff.api.controller.advice.dto.ErrorResponse;
import com.kickoff.api.elasticsearch.web.ElasticSearchFoundException;
import com.kickoff.core.member.service.MemberException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception)
    {
        log.error("MethodArgumentNotValidException");
        String message = exception.getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining());
        return ErrorResponse.badRequest(message);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException exception)
    {
        log.error("HttpMessageNotReadableException handle : {}",exception.getMessage());
        String message = "request parsing 에러";
        return ErrorResponse.badRequest(message);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResponse handleIllegalArgumentException(IllegalArgumentException exception)
    {
        log.error("handleIllegalArgumentException handle : {}",exception.getMessage());
        return ErrorResponse.badRequest(exception.getMessage());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MemberException.class)
    public ErrorResponse handleMemberException(MemberException exception)
    {
        log.error("MemberException handle : {}",exception.getMessage());
        return ErrorResponse.badRequest(exception.getMessage());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ElasticSearchFoundException.class)
    public ErrorResponse handleElasticSearchFoundException(ElasticSearchFoundException exception)
    {
        return ErrorResponse.error(exception.getMessage());
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception exception)
    {
        log.error("Error Code : {}", exception.getMessage());
        return ErrorResponse.error(exception.getMessage());
    }
}
