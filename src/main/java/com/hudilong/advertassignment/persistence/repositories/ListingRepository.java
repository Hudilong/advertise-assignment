package com.hudilong.advertassignment.persistence.repositories;

import com.hudilong.advertassignment.domain.entities.ListingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ListingRepository extends JpaRepository<ListingEntity, UUID> {
}
