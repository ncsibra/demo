package com.epam.receipt.service;

import com.epam.receipt.dto.ItemDetails;
import com.epam.receipt.model.RequestItem;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;

@Component
public class TaxCalculator {

    private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    public TaxPrice calculate(RequestItem item, ItemDetails itemDetails) {
        BigDecimal price = item.getPrice();
        BigDecimal quantity = new BigDecimal(item.getQuantity());
        BigDecimal taxMultiplier = itemDetails.getCategory().getTaxPercentage().divide(ONE_HUNDRED);
        BigDecimal tax = price.multiply(taxMultiplier);
        BigDecimal taxAmount = tax.multiply(quantity);
        BigDecimal priceAmount = price.add(tax).multiply(quantity);

        return new TaxPrice.Builder().withPriceAmount(priceAmount.setScale(2, HALF_UP)).withTaxAmont(taxAmount.setScale(2, HALF_UP)).build();
    }
}
