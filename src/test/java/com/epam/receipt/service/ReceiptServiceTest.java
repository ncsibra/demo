package com.epam.receipt.service;

import com.epam.receipt.dao.ItemRepository;
import com.epam.receipt.dto.ItemDetails;
import com.epam.receipt.exception.ItemNotFoundException;
import com.epam.receipt.model.RequestItem;
import com.epam.receipt.model.TaxItem;
import com.epam.receipt.model.TaxReceipt;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReceiptServiceTest {

    @Mock
    private ItemRepository itemRepository;
    @Mock
    private TaxCalculator taxCalculator;
    @Mock
    private RequestItem requestItem;
    @Mock
    private ItemDetails itemDetails;
    @Mock
    private TaxPrice taxPrice;

    private ReceiptService underTest;

    @Before
    public void setUp() {
        underTest = new ReceiptService(itemRepository, taxCalculator);

        when(requestItem.getId()).thenReturn(1);
        when(itemRepository.findById(anyInt())).thenReturn(itemDetails);
        when(taxCalculator.calculate(any(RequestItem.class), any(ItemDetails.class))).thenReturn(taxPrice);
        when(taxPrice.getPriceAmount()).thenReturn(BigDecimal.TEN);
        when(taxPrice.getTaxAmount()).thenReturn(BigDecimal.ONE);
    }

    @Test(expected = ItemNotFoundException.class)
    public void shouldThrowExceptionWhenItemNotFound() {
        // Given
        when(itemRepository.findById(anyInt())).thenReturn(null);

        // When
        underTest.createTaxReceipt(singletonList(requestItem));

        // Then
    }

    @Test
    public void shouldRetrieveItemFromRepository() {
        // Given

        // When
        underTest.createTaxReceipt(singletonList(requestItem));

        // Then
        verify(itemRepository).findById(anyInt());
    }

    @Test
    public void shouldCalculatePriceWithTaxCalculator() {
        // Given

        // When
        underTest.createTaxReceipt(singletonList(requestItem));

        // Then
        verify(taxCalculator).calculate(any(RequestItem.class), any(ItemDetails.class));
    }

    @Test
    public void shouldReturnWithValidTaxReceipt() {
        // Given
        String expectedName = "hello";
        BigDecimal expectedPrice = BigDecimal.TEN;
        BigDecimal expectedTaxAmount = new BigDecimal("2");
        BigDecimal expectedPriceAmount = new BigDecimal("20");
        Integer expectedQuantity = 1;
        TaxItem expectedItem = new TaxItem.Builder().withName(expectedName).withPrice(expectedPrice).withQuantity(expectedQuantity).build();

        when(requestItem.getName()).thenReturn(expectedName);
        when(requestItem.getQuantity()).thenReturn(expectedQuantity);

        // When
        TaxReceipt result = underTest.createTaxReceipt(asList(requestItem, requestItem));

        // Then
        assertThat(result.getItems()).hasSize(2);
        assertThat(result.getItems().get(0)).isEqualToComparingFieldByFieldRecursively(expectedItem);
        assertThat(result.getSalesTaxes()).isEqualTo(expectedTaxAmount);
        assertThat(result.getTotal()).isEqualTo(expectedPriceAmount);
    }

}