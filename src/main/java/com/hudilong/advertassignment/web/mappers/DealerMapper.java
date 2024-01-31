package com.hudilong.advertassignment.web.mappers;

import com.hudilong.advertassignment.domain.entities.DealerEntity;
import com.hudilong.advertassignment.web.dtos.DealerDto;

public class DealerMapper {

    public static DealerEntity mapDtoToEntity(DealerDto dealerDto) {
        return DealerEntity.builder()
                .id(dealerDto.id())
                .name(dealerDto.name())
                .build();
    }

    public static DealerDto mapEntityToDto(DealerEntity dealerEntity) {
        return new DealerDto(
                dealerEntity.getId(),
                dealerEntity.getName()
        );
    }
}
