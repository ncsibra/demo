package com.epam.receipt.resource;

import com.epam.receipt.exception.InvalidRequestException;
import com.epam.receipt.model.ReceiptRequest;
import com.epam.receipt.model.RequestItem;
import com.epam.receipt.service.ReceiptService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;

import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReceiptResourceTest {

    @Mock
    private ReceiptService receiptService;
    @Mock
    private ReceiptRequest receiptRequest;
    @Mock
    private BindingResult bindingResult;

    private ReceiptResource underTest;

    @Before
    public void setUp() {
        underTest = new ReceiptResource(receiptService);
    }

    @Test(expected = InvalidRequestException.class)
    public void shouldThrowExcepctionWhenRequestIsInvalid() {
        // Given
        when(bindingResult.hasFieldErrors()).thenReturn(true);

        // When
        underTest.createReceipt(receiptRequest, bindingResult);

        // Then
    }

    @Test
    public void shouldCallReceiptServiceWhenRequestIsValid() {
        // Given
        when(bindingResult.hasFieldErrors()).thenReturn(false);

        // When
        underTest.createReceipt(receiptRequest, bindingResult);

        // Then
        verify(receiptService).createTaxReceipt(anyListOf(RequestItem.class));
    }
}