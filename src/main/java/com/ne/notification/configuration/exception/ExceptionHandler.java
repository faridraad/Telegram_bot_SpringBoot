package com.ne.notification.configuration.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ne.notification.configuration.resources.ApplicationProperties;
import com.ne.notification.model.dto.base.BaseDTO;
import com.ne.notification.model.dto.base.MetaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.Set;

@ControllerAdvice
public class ExceptionHandler {
    @Autowired
    ApplicationProperties applicationProperties;

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<BaseDTO> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
        BaseDTO baseDTO = new BaseDTO();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {

            MetaDTO metaDTO = new MetaDTO(
                    applicationProperties.getCode("validation-error-code"),
//                    applicationProperties.getProperty(error.getField()) + " " + applicationProperties.getProperty("validation-error-text")
                    error.getField() + " " + applicationProperties.getProperty("validation-error-text")
            );
            baseDTO.setMeta(metaDTO);
            break;
        }
        return new ResponseEntity<>(baseDTO, HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<BaseDTO> handleConstraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {
        BaseDTO baseDTO = new BaseDTO();
        String field = "field";
        Set<ConstraintViolation<?>> cvs = ex.getConstraintViolations();
        for (ConstraintViolation<?> cv : cvs) {
            field = (cv.getPropertyPath().toString());
            field = (field.substring(field.indexOf(".") + 1, field.length()));
        }
        MetaDTO metaDTO = new MetaDTO(
                applicationProperties.getCode("validation-error-code"),
                String.format(
                        applicationProperties.getProperty("validation-error-text"),
                        applicationProperties.getProperty(field)
                )
        );
        baseDTO.setMeta(metaDTO);
        return new ResponseEntity<>(baseDTO, HttpStatus.BAD_REQUEST);
    }


    // --> Custom exceptions
    @org.springframework.web.bind.annotation.ExceptionHandler(ServiceException.class)
    public final ResponseEntity<BaseDTO> handleAllExceptions(ServiceException ex, WebRequest request) {
        return new ResponseEntity<>(
                new BaseDTO(
                        new MetaDTO(
                                ex.getCode(),
                                ex.getMessage()
                        )
                ), ex.getHttpStatus() == null ? HttpStatus.INTERNAL_SERVER_ERROR : ex.getHttpStatus()
        );
    }

    // --> Handler not found exceptions
    @org.springframework.web.bind.annotation.ExceptionHandler(NoHandlerFoundException.class)
    public final ResponseEntity<BaseDTO> handleAllExceptions(NoHandlerFoundException ex, WebRequest request) {
        return new ResponseEntity<>(
                new BaseDTO(
                        new MetaDTO(
                                HttpStatus.NOT_FOUND.value(),
                                ex.getMessage()
                        )
                ), HttpStatus.NOT_FOUND
        );
    }

    // --> General exceptions
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    //This annotation shows this method can handle all exceptions or a specific type of exception e.g. IOExceptions
    public final ResponseEntity<BaseDTO> handleAllExceptions(Exception ex, WebRequest request) throws IOException {
        return new ResponseEntity<>(
                new BaseDTO(
                        new MetaDTO(
                                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                ex.getMessage() == null ? applicationProperties.getProperty("unknown-error-text") : ex.getMessage()
                        )
                ), HttpStatus.INTERNAL_SERVER_ERROR
        );

    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MissingServletRequestParameterException.class)
    public final ResponseEntity<BaseDTO> handleMissingParameterException(MissingServletRequestParameterException ex, WebRequest request) {
//        Object convertedFieldName = applicationProperties.getProperty(ex.getParameterName());
        Object convertedFieldName = ex.getParameterName();
        return new ResponseEntity<>(
                new BaseDTO(new MetaDTO(
                        applicationProperties.getCode("validation-error-code"),
                        convertedFieldName + " " + applicationProperties.getProperty("validation-error-text")
                ))
                , HttpStatus.BAD_REQUEST
        );
    }

    // --> Runtime exceptions
    @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<BaseDTO> handleAllExceptions(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(
                new BaseDTO(
                        new MetaDTO(

                                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                ex.getMessage() == null ? applicationProperties.getProperty("unknown-error-text") : ex.getMessage()
                        )
                ), HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    // --> Server or machine errors
    @org.springframework.web.bind.annotation.ExceptionHandler(Error.class)
    public final ResponseEntity<BaseDTO> handleAllExceptions(Error ex, WebRequest request) {
        return new ResponseEntity<>(
                new BaseDTO(
                        new MetaDTO(
                                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                ex.getMessage()
                        )
                ), HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    // --> Server or machine errors
    @org.springframework.web.bind.annotation.ExceptionHandler(HttpClientErrorException.class)
    public final ResponseEntity<BaseDTO> handleHttpClientErrorException(HttpClientErrorException ex, WebRequest request) throws IOException {
        return new ResponseEntity<>(
                new ObjectMapper().readValue(ex.getStatusText(), BaseDTO.class), ex.getStatusCode()
        );
    }
}
