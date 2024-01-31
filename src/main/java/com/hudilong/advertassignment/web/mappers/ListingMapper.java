package com.hudilong.advertassignment.web.mappers;

import com.hudilong.advertassignment.domain.entities.DealerEntity;
import com.hudilong.advertassignment.domain.entities.ListingEntity;
import com.hudilong.advertassignment.persistence.repositories.DealerRepository;
import com.hudilong.advertassignment.web.dtos.ListingDto;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

public final class ListingMapper {

    private DealerRepository dealerRepository;

    public ListingMapper(DealerRepository dealerRepository) {
        this.dealerRepository = dealerRepository;
    };
    public ListingEntity mapDtoToEntity(ListingDto listingDto) {
        DealerEntity dealerEntity = dealerRepository.findById(listingDto.dealer().id())
                .orElseThrow(() -> new EntityNotFoundException("Dealer not found"));
        return ListingEntity.builder()
                .dealer(dealerEntity)
                .vehicle(listingDto.vehicle())
                .price(listingDto.price())
                .build();
    }

    public ListingDto mapEntityToDto(ListingEntity listingEntity) {
        return new ListingDto(
                listingEntity.getId(),
                DealerMapper.mapEntityToDto(listingEntity.getDealer()),
                listingEntity.getVehicle(),
                listingEntity.getPrice(),
                listingEntity.getCreatedAt(),
                listingEntity.getState()
        );
    }
}
