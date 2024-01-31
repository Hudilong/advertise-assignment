package com.hudilong.advertassignment.domain.services;

import com.hudilong.advertassignment.web.dtos.DealerDto;

import java.util.UUID;

public interface DealerService {

    DealerDto create(DealerDto dealerDto);

    DealerDto findById(UUID dealerId);

    void delete(UUID dealerId);

    void deleteAll();
}
