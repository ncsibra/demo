package com.epam.receipt.dao;

import com.epam.receipt.dto.ItemDetails;
import org.springframework.data.keyvalue.repository.KeyValueRepository;

public interface ItemRepository extends KeyValueRepository<ItemDetails, Integer> {

    ItemDetails findById(Integer id);

    ItemDetails findByName(String name);
}
