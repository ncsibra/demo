package com.epam.receipt.service;

import static java.math.RoundingMode.HALF_UP;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.epam.receipt.dto.ItemDetails;
import com.epam.receipt.model.RequestItem;
import com.epam.receipt.value.TaxPrice;

@Component
public class TaxCalculator {

    public TaxPrice calculate(RequestItem item, ItemDetails itemDetails) {
        BigDecimal price = item.getPrice();
        BigDecimal quantity = new BigDecimal(item.getQuantity());
        BigDecimal taxPercentage = itemDetails.getCategory().getTaxPercentage();
        BigDecimal tax = price.multiply(taxPercentage);
        BigDecimal taxAmount = tax.multiply(quantity);
        BigDecimal priceAmount = price.add(tax).multiply(quantity);

        return new TaxPrice.Builder()
                .withPriceAmount(priceAmount.setScale(2, HALF_UP))
                .withTaxAmont(taxAmount.setScale(2, HALF_UP))
                .build();
    }
}
