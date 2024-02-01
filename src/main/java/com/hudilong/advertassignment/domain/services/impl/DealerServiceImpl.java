package com.hudilong.advertassignment.domain.services.impl;

import com.hudilong.advertassignment.domain.entities.DealerEntity;
import com.hudilong.advertassignment.domain.services.DealerService;
import com.hudilong.advertassignment.persistence.repositories.DealerRepository;
import com.hudilong.advertassignment.web.dtos.DealerDto;
import com.hudilong.advertassignment.web.mappers.DealerMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DealerServiceImpl implements DealerService {

    private final DealerRepository dealerRepository;
    private final DealerMapper dealerMapper;

    public DealerServiceImpl(DealerRepository dealerRepository, DealerMapper dealerMapper) {
        this.dealerRepository = dealerRepository;
        this.dealerMapper = dealerMapper;
    }
    @Override
    public DealerDto create(DealerDto dealerDto) {
        DealerEntity entity = dealerMapper.mapDtoToEntity(dealerDto);
        DealerEntity savedEntity = dealerRepository.save(entity);
        return dealerMapper.mapEntityToDto(savedEntity);
    }

    @Override
    public List<DealerDto> findAll() {
        List<DealerEntity> entities = dealerRepository.findAll();
        return entities.stream().map(dealerMapper::mapEntityToDto).collect(Collectors.toList());
    }

    @Override
    public DealerDto findById(UUID dealerId) {
        DealerEntity entity = dealerRepository.findById(dealerId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.join(" ", "Dealer with id:", dealerId.toString(), "not found")));
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
