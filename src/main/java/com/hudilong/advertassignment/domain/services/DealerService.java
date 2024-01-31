package com.hudilong.advertassignment.domain.services;

import com.hudilong.advertassignment.web.dtos.DealerDto;

import java.util.List;
import java.util.UUID;

public interface DealerService {

    DealerDto create(DealerDto dealerDto);

    List<DealerDto> findAll();

    DealerDto findById(UUID dealerId);

    void delete(UUID dealerId);

    void deleteAll();
}
