package com.hudilong.advertassignment.web.mappers;

import com.hudilong.advertassignment.domain.entities.DealerEntity;
import com.hudilong.advertassignment.domain.entities.ListingEntity;
import com.hudilong.advertassignment.persistence.repositories.DealerRepository;
import com.hudilong.advertassignment.web.dtos.ListingDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public final class ListingMapper {

    private final DealerRepository dealerRepository;
    private final DealerMapper dealerMapper;

    public ListingMapper(DealerRepository dealerRepository, DealerMapper dealerMapper) {
        this.dealerRepository = dealerRepository;
        this.dealerMapper = dealerMapper;
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
                dealerMapper.mapEntityToDto(listingEntity.getDealer()),
                listingEntity.getVehicle(),
                listingEntity.getPrice(),
                listingEntity.getCreatedAt(),
                listingEntity.getState()
        );
    }
}
