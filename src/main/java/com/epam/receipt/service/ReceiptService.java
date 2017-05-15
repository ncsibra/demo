package com.epam.receipt.service;

import com.epam.receipt.dao.ItemRepository;
import com.epam.receipt.dto.ItemDetails;
import com.epam.receipt.exception.ItemNotFoundException;
import com.epam.receipt.model.RequestItem;
import com.epam.receipt.model.TaxItem;
import com.epam.receipt.model.TaxReceipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@Service
public class ReceiptService {

    private final ItemRepository itemRepository;
    private final TaxCalculator taxCalculator;

    @Autowired
    public ReceiptService(ItemRepository itemRepository, TaxCalculator taxCalculator) {
        this.itemRepository = itemRepository;
        this.taxCalculator = taxCalculator;
    }

    public TaxReceipt createTaxReceipt(List<RequestItem> requestItems) {
        List<TaxItem> taxItems = new LinkedList<>();
        BigDecimal salesTaxes = new BigDecimal(0);
        BigDecimal total = new BigDecimal(0);

        for (RequestItem item : requestItems) {
            ItemDetails itemDetails = getItemDetails(item);
            TaxPrice taxPrice = taxCalculator.calculate(item, itemDetails);

            taxItems.add(createTaxItem(item, taxPrice));

            salesTaxes = salesTaxes.add(taxPrice.getTaxAmount());
            total = total.add(taxPrice.getPriceAmount());
        }

        return new TaxReceipt.Builder().withItems(taxItems).withSalesTaxes(salesTaxes).withTotal(total).build();
    }

    private ItemDetails getItemDetails(RequestItem item) {
        ItemDetails details = itemRepository.findById(item.getId());
        if (details == null) {
            throw new ItemNotFoundException(item);
        }

        return details;
    }

    private TaxItem createTaxItem(RequestItem item, TaxPrice taxPrice) {
        return new TaxItem.Builder().withName(item.getName()).withPrice(taxPrice.getPriceAmount()).withQuantity(item.getQuantity()).build();
    }
}
