package com.hudilong.advertassignment.web.dtos;

import com.hudilong.advertassignment.domain.enums.State;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ListingDto(
        UUID id,
        UUID dealerId,
        String vehicle,
        BigDecimal price,
        LocalDateTime createdAt,
        State state
) {
}
