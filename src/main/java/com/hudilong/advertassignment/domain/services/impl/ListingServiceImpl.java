package com.hudilong.advertassignment.domain.services.impl;

import com.hudilong.advertassignment.domain.entities.ListingEntity;
import com.hudilong.advertassignment.domain.enums.State;
import com.hudilong.advertassignment.domain.services.ListingService;
import com.hudilong.advertassignment.persistence.repositories.ListingRepository;
import com.hudilong.advertassignment.web.dtos.ListingDto;
import com.hudilong.advertassignment.web.mappers.ListingMapper;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.UUID;

public class ListingServiceImpl implements ListingService {

    private final ListingRepository listingRepository;
    private final ListingMapper listingMapper;

    public ListingServiceImpl(ListingRepository listingRepository, ListingMapper listingMapper) {
        this.listingRepository = listingRepository;
        this.listingMapper = listingMapper;
    }

    @Override
    public ListingDto create(ListingDto listingDto) {
        ListingEntity entity = listingMapper.mapDtoToEntity(listingDto);
        ListingEntity savedEntity = listingRepository.save(entity);
        return listingMapper.mapEntityToDto(savedEntity);
    }

    @Override
    public void update(UUID listingId, ListingDto listingDto) {
        if(!listingRepository.existsById(listingId)){
            throw new EntityNotFoundException("Listing not found");
        };
        ListingEntity entity = listingMapper.mapDtoToEntity(listingDto);
        entity.setId(listingId);
        listingRepository.save(entity);
    }

    @Override
    public List<ListingDto> findAllByDealerId(UUID dealerId, State state) {
        return null;
    }

    @Override
    public void publish(UUID listingId) {
        ListingEntity listingEntity = listingRepository.findById(listingId)
                .orElseThrow(() -> new EntityNotFoundException("Listing not found"));
        listingEntity.setState(State.PUBLISHED);
    }

    @Override
    public void hide(UUID listingId) {
        ListingEntity listingEntity = listingRepository.findById(listingId)
                .orElseThrow(() -> new EntityNotFoundException("Listing not found"));
        listingEntity.setState(State.DRAFT);
    }
}
