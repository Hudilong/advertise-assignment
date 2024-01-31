package com.hudilong.advertassignment.persistence.repositories;

import com.hudilong.advertassignment.domain.entities.ListingEntity;
import com.hudilong.advertassignment.domain.enums.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@Repository
public interface ListingRepository extends JpaRepository<ListingEntity, UUID> {

    @Query(value = "SELECT * FROM listings WHERE dealer_id = :dealerId AND state = :state", nativeQuery = true)
    List<ListingEntity> findAllListingsByDealerAndState(@Param("dealerId") UUID dealerId, @Param("state") State state);
}
