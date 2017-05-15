package com.epam.receipt.service;

import com.epam.receipt.dto.ItemDetails;
import com.epam.receipt.model.RequestItem;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Answers.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaxCalculatorTest {

    @Mock
    private RequestItem requestItem;
    @Mock(answer = RETURNS_DEEP_STUBS)
    private ItemDetails itemDetails;

    private TaxCalculator underTest;

    @Before
    public void setUp() {
        underTest = new TaxCalculator();

        when(requestItem.getQuantity()).thenReturn(1);
        when(itemDetails.getCategory().getTaxPercentage()).thenReturn(new BigDecimal("17.5"));
    }

    @Test
    public void shouldRoundDownWhenFractionLessThenFive() {
        // Given
        BigDecimal expectedPriceAmount = new BigDecimal("34.65");
        BigDecimal expectedTaxAmount = new BigDecimal("5.16");

        when(requestItem.getPrice()).thenReturn(new BigDecimal("29.49"));

        // When
        TaxPrice result = underTest.calculate(requestItem, itemDetails);

        // Then
        assertThat(result.getPriceAmount()).isEqualTo(expectedPriceAmount);
        assertThat(result.getTaxAmount()).isEqualTo(expectedTaxAmount);
    }

    @Test
    public void shouldRoundUpWhenFractionMoreThenFive() {
        // Given
        BigDecimal expectedPriceAmount = new BigDecimal("34.69");
        BigDecimal expectedTaxAmount = new BigDecimal("5.17");

        when(requestItem.getPrice()).thenReturn(new BigDecimal("29.52"));

        // When
        TaxPrice result = underTest.calculate(requestItem, itemDetails);

        // Then
        assertThat(result.getPriceAmount()).isEqualTo(expectedPriceAmount);
        assertThat(result.getTaxAmount()).isEqualTo(expectedTaxAmount);
    }

    @Test
    public void shouldRoundUpWhenFractionIsFive() {
        // Given
        BigDecimal expectedPriceAmount = new BigDecimal("1.27");
        BigDecimal expectedTaxAmount = new BigDecimal("0.27");

        when(requestItem.getPrice()).thenReturn(new BigDecimal("1"));
        when(itemDetails.getCategory().getTaxPercentage()).thenReturn(new BigDecimal("26.5"));

        // When
        TaxPrice result = underTest.calculate(requestItem, itemDetails);

        // Then
        assertThat(result.getPriceAmount()).isEqualTo(expectedPriceAmount);
        assertThat(result.getTaxAmount()).isEqualTo(expectedTaxAmount);
    }
}