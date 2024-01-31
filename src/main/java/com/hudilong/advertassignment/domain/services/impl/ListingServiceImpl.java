package com.hudilong.advertassignment.domain.services.impl;

import com.hudilong.advertassignment.domain.entities.ListingEntity;
import com.hudilong.advertassignment.domain.enums.State;
import com.hudilong.advertassignment.domain.services.ListingService;
import com.hudilong.advertassignment.persistence.repositories.DealerRepository;
import com.hudilong.advertassignment.persistence.repositories.ListingRepository;
import com.hudilong.advertassignment.web.dtos.ListingDto;
import com.hudilong.advertassignment.web.mappers.ListingMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ListingServiceImpl implements ListingService {

    private final ListingRepository listingRepository;
    private final DealerRepository dealerRepository;
    private final ListingMapper listingMapper;

    public ListingServiceImpl(ListingRepository listingRepository, DealerRepository dealerRepository, ListingMapper listingMapper) {
        this.listingRepository = listingRepository;
        this.dealerRepository = dealerRepository;
        this.listingMapper = listingMapper;
    }

    @Override
    public ListingDto create(ListingDto listingDto) {
        ListingEntity entity = listingMapper.mapDtoToEntity(listingDto);
        ListingEntity savedEntity = listingRepository.save(entity);
        return listingMapper.mapEntityToDto(savedEntity);
    }

    @Override
    public void update(ListingDto listingDto) {
        if(!listingRepository.existsById(listingDto.id())){
            throw new EntityNotFoundException("Listing not found");
        };
        ListingEntity entity = listingMapper.mapDtoToEntity(listingDto);
        entity.setId(listingDto.id());
        listingRepository.save(entity);
    }

    @Override
    public List<ListingDto> findAllByDealerId(UUID dealerId, State state) {
       if(!dealerRepository.existsById(dealerId)) {
           throw new EntityNotFoundException("Dealer not found");
       }
       List<ListingEntity> entities = listingRepository.findAllListingsByDealerAndState(dealerId, state);
       return entities.stream().map(listingMapper::mapEntityToDto).collect(Collectors.toList());
    }

    @Override
    public void publish(UUID listingId) {
        ListingEntity listingEntity = listingRepository.findById(listingId)
                .orElseThrow(() -> new EntityNotFoundException("Listing not found"));
        listingEntity.setState(State.PUBLISHED);
        listingRepository.save(listingEntity);
    }

    @Override
    public void hide(UUID listingId) {
        ListingEntity listingEntity = listingRepository.findById(listingId)
                .orElseThrow(() -> new EntityNotFoundException("Listing not found"));
        listingEntity.setState(State.DRAFT);
        listingRepository.save(listingEntity);
    }
}
