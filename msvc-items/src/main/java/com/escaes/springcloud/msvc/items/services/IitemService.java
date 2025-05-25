package com.escaes.springcloud.msvc.items.services;

import java.util.List;
import java.util.Optional;

import com.escaes.springcloud.msvc.items.models.Item;

public interface IitemService {
    List<Item>findAll();
    Optional<Item>findById(Long id);
}
