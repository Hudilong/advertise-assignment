package com.hudilong.advertassignment.domain.services;

import com.hudilong.advertassignment.domain.enums.State;
import com.hudilong.advertassignment.web.dtos.ListingDto;

import java.util.List;
import java.util.UUID;

public interface ListingService {
    ListingDto create(ListingDto listingDto);

    void update(UUID listingId, ListingDto listingDto);

    List<ListingDto> findAllByDealerId(UUID dealerId, State state);

    void publish(UUID listingId);

    void hide(UUID listingId);

}
