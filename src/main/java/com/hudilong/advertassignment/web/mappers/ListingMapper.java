package com.hudilong.advertassignment.web.mappers;

import com.hudilong.advertassignment.domain.entities.DealerEntity;
import com.hudilong.advertassignment.domain.entities.ListingEntity;
import com.hudilong.advertassignment.web.dtos.ListingDto;

public final class ListingMapper {
    public static ListingEntity mapDtoToEntity(ListingDto listingDto) {
        DealerEntity dealerEntity = DealerRepository.findOne(listingDto.dealer().id());
        return ListingEntity.builder()
                .dealer(dealerEntity)
                .vehicle(listingDto.vehicle())
                .price(listingDto.price())
                .build();
    }

    public static ListingDto mapEntityToDto(ListingEntity listingEntity) {
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
