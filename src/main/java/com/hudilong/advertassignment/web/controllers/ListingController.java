package com.hudilong.advertassignment.web.controllers;

import com.hudilong.advertassignment.domain.enums.State;
import com.hudilong.advertassignment.domain.services.ListingService;
import com.hudilong.advertassignment.web.dtos.ListingDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/advertising/listings")
public class ListingController {

    private ListingService listingService;

    public ListingController(ListingService listingService) {
        this.listingService = listingService;
    }

    @PostMapping(path = "/")
    public ResponseEntity<ListingDto> createListing(@RequestBody @Validated ListingDto listingDto) {
        ListingDto savedListing = listingService.create(listingDto);
        return new ResponseEntity<>(savedListing, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{dealerId}/{state}")
    public ResponseEntity<List<ListingDto>> getListingFromDealerAndState(@PathVariable("dealerId") UUID dealerId, @PathVariable("state") State state) {
        List<ListingDto> listings = listingService.findAllByDealerId(dealerId, state);
        return new ResponseEntity<>(listings, HttpStatus.OK);
    }

    @PutMapping(path = "/")
    public ResponseEntity<Void> updateListing(@RequestBody @Validated ListingDto listingDto) {
        listingService.update(listingDto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/publish/{id}")
    public ResponseEntity<Void> publishListing(@PathVariable("id") UUID id) {
        listingService.publish(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/hide/{id}")
    public ResponseEntity<Void> hideListing(@PathVariable("id") UUID id) {
        listingService.hide(id);
        return ResponseEntity.noContent().build();
    }
}
