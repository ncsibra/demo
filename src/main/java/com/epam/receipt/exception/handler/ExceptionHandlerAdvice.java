package com.epam.receipt.exception.handler;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.epam.receipt.exception.InvalidRequestException;
import com.epam.receipt.exception.ItemNotFoundException;
import com.epam.receipt.model.RequestItem;
import com.epam.receipt.model.exception.Error;
import com.epam.receipt.model.exception.ItemNotFoundDetail;
import com.epam.receipt.model.exception.ValidationDetail;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler({InvalidRequestException.class})
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public Error<List<ValidationDetail>> handleInvalidRequest(InvalidRequestException exception) {
        return new Error<>("Request validation failed.", createValidationDetails(exception.getResult()));
    }

    @ExceptionHandler({ItemNotFoundException.class})
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public Error<ItemNotFoundDetail> handleItemNotFound(ItemNotFoundException exception) {
        RequestItem item = exception.getItem();
        return new Error<>("Unable to find item based on provided properties.", new ItemNotFoundDetail(item.getId(), item.getName()));
    }

    private List<ValidationDetail> createValidationDetails(BindingResult result) {
        return result.getFieldErrors().stream()
                .map(error -> new ValidationDetail(error.getField(), error.getDefaultMessage()))
                .collect(toList());
    }

}
