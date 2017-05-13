package com.epam.receipt.dao;

import org.springframework.data.keyvalue.repository.KeyValueRepository;

import com.epam.receipt.dto.ItemDetails;

public interface ItemRepository extends KeyValueRepository<ItemDetails, Integer> {

    ItemDetails findById(Integer id);

    ItemDetails findByName(String name);
}
