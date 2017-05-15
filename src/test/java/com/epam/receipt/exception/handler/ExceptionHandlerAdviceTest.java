package com.epam.receipt.exception.handler;

import com.epam.receipt.exception.InvalidRequestException;
import com.epam.receipt.exception.ItemNotFoundException;
import com.epam.receipt.model.RequestItem;
import com.epam.receipt.model.exception.Error;
import com.epam.receipt.model.exception.ItemNotFoundDetail;
import com.epam.receipt.model.exception.ValidationDetail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExceptionHandlerAdviceTest {

    private ExceptionHandlerAdvice underTest;

    @Before
    public void setUp() {
        underTest = new ExceptionHandlerAdvice();
    }

    @Test
    public void shouldCreateValidationErrorWithDetails() {
        // Given
        String expectedPath = "hello";
        String expectedMessage = "world";
        InvalidRequestException exception = mock(InvalidRequestException.class);
        BindingResult bindingResult = mock(BindingResult.class);
        FieldError error = mock(FieldError.class);

        when(exception.getResult()).thenReturn(bindingResult);
        when(bindingResult.getFieldErrors()).thenReturn(singletonList(error));
        when(error.getField()).thenReturn(expectedPath);
        when(error.getDefaultMessage()).thenReturn(expectedMessage);

        // When
        Error<List<ValidationDetail>> result = underTest.handleInvalidRequest(exception);

        // Then
        assertThat(result.getMessage()).isNotBlank();
        assertThat(result.getDetails()).hasSize(1);

        ValidationDetail validationDetail = result.getDetails().get(0);
        assertThat(validationDetail.getMessage()).isEqualTo(expectedMessage);
        assertThat(validationDetail.getPath()).isEqualTo(expectedPath);
    }

    @Test
    public void shouldCreateItemNotFoundErrorWithDetails() {
        // Given
        Integer expectedId = 1;
        String expectedName = "hello";
        RequestItem requestItem = mock(RequestItem.class);
        ItemNotFoundException exception = mock(ItemNotFoundException.class);

        when(exception.getItem()).thenReturn(requestItem);
        when(requestItem.getId()).thenReturn(expectedId);
        when(requestItem.getName()).thenReturn(expectedName);

        // When
        Error<ItemNotFoundDetail> result = underTest.handleItemNotFound(exception);

        // Then
        assertThat(result.getMessage()).isNotBlank();

        ItemNotFoundDetail itemNotFoundDetail = result.getDetails();
        assertThat(itemNotFoundDetail.getId()).isEqualTo(expectedId);
        assertThat(itemNotFoundDetail.getName()).isEqualTo(expectedName);
    }
}