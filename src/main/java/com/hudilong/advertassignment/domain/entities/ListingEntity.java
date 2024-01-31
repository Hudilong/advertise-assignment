package com.hudilong.advertassignment.domain.entities;


import com.hudilong.advertassignment.domain.enums.State;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ListingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID dealerId;

    private String vehicle;

    private BigDecimal price;

    private LocalDateTime createdAt;

    private State state;
}
