package com.hudilong.advertassignment.persistence.repositories;

import com.hudilong.advertassignment.domain.entities.DealerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DealerRepository extends JpaRepository<DealerEntity, UUID> {
}
