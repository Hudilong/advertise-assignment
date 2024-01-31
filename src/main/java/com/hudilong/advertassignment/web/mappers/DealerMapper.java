package com.hudilong.advertassignment.web.mappers;

import com.hudilong.advertassignment.domain.entities.DealerEntity;
import com.hudilong.advertassignment.web.dtos.DealerDto;
import org.springframework.stereotype.Component;

@Component
public class DealerMapper {

    public DealerEntity mapDtoToEntity(DealerDto dealerDto) {
        return DealerEntity.builder()
                .id(dealerDto.id())
                .name(dealerDto.name())
                .build();
    }

    public DealerDto mapEntityToDto(DealerEntity dealerEntity) {
        return new DealerDto(
                dealerEntity.getId(),
                dealerEntity.getName()
        );
    }
}
