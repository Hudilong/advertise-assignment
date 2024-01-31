package com.hudilong.advertassignment.domain.services.impl;

import com.hudilong.advertassignment.domain.entities.DealerEntity;
import com.hudilong.advertassignment.domain.services.DealerService;
import com.hudilong.advertassignment.persistence.repositories.DealerRepository;
import com.hudilong.advertassignment.web.dtos.DealerDto;
import com.hudilong.advertassignment.web.mappers.DealerMapper;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;
import java.util.UUID;

public class DealerServiceImpl implements DealerService {

    private DealerRepository dealerRepository;
    private DealerMapper dealerMapper;

    public DealerServiceImpl(DealerRepository dealerRepository, DealerMapper dealerMapper) {

    };
    @Override
    public DealerDto create(DealerDto dealerDto) {
        DealerEntity entity = dealerMapper.mapDtoToEntity(dealerDto);
        DealerEntity savedEntity = dealerRepository.save(entity);
        return dealerMapper.mapEntityToDto(savedEntity);
    }

    @Override
    public DealerDto findById(UUID dealerId) {
        DealerEntity entity = dealerRepository.findById(dealerId)
                .orElseThrow(() -> new EntityNotFoundException("Dealer not found"));
        return dealerMapper.mapEntityToDto(entity);
    }

    @Override
    public void delete(UUID dealerId) {
        dealerRepository.deleteById(dealerId);
    }

    @Override
    public void deleteAll() {
        dealerRepository.deleteAll();
    }
}
