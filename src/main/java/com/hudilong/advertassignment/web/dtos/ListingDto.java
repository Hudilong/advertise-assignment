package com.hudilong.advertassignment.web.dtos;

import com.hudilong.advertassignment.domain.entities.DealerEntity;
import com.hudilong.advertassignment.domain.enums.State;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ListingDto(
        UUID id,
        DealerDto dealer,
        String vehicle,
        BigDecimal price,
        LocalDateTime createdAt,
        State state
) {
}
