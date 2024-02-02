package com.hudilong.advertassignment.web.controllers;

import com.hudilong.advertassignment.domain.enums.State;
import com.hudilong.advertassignment.domain.exceptions.TierLimitReachedException;
import com.hudilong.advertassignment.domain.services.ListingService;
import com.hudilong.advertassignment.web.dtos.DealerDto;
import com.hudilong.advertassignment.web.dtos.ErrorResponseDto;
import com.hudilong.advertassignment.web.dtos.ListingDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/advertising/listings")
public class ListingController {

    private final ListingService listingService;

    public ListingController(ListingService listingService) {
        this.listingService = listingService;
    }

    @PostMapping(path = "/")
    @Operation(description = "Creates a new listing from the provided information.")
    @ApiResponse(
            responseCode = "201",
            description = "Listing created",
            content = {@Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ListingDto.class))})
    public ResponseEntity<ListingDto> createListing(@RequestBody @Validated ListingDto listingDto) {
        ListingDto savedListing = listingService.create(listingDto);
        return new ResponseEntity<>(savedListing, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{dealerId}/{state}")
    @Operation(description = "Retrieves listings of a dealer with a certain state")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved listings",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DealerDto.class))}),
            @ApiResponse(
                    responseCode = "404",
                    description = "Dealer was not found",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDto.class))})})
    public ResponseEntity<List<ListingDto>> getListingFromDealerAndState(
            @PathVariable("dealerId") UUID dealerId,
            @PathVariable("state") State state) throws EntityNotFoundException {
        List<ListingDto> listings = listingService.findAllByDealerId(dealerId, state);
        return new ResponseEntity<>(listings, HttpStatus.OK);
    }

    @PutMapping(path = "/")
    @Operation(description = "Updates a listing with the provided information")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Successfully updated listing"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Listing was not found",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(
                    responseCode = "403",
                    description = "Dealer's tier limit is already reached",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDto.class))})})
    public ResponseEntity<Void> updateListing(@RequestBody @Validated ListingDto listingDto) throws EntityNotFoundException, TierLimitReachedException {
        listingService.update(listingDto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/publish/{id}")
    @Operation(description = "Publishes a listing")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Successfully published listing"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Listing was not found",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(
                    responseCode = "403",
                    description = "Dealer's tier limit is already reached",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDto.class))})})
    public ResponseEntity<Void> publishListing(@PathVariable("id") UUID id) throws EntityNotFoundException, TierLimitReachedException {
        listingService.publish(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/hide/{id}")
    @Operation(description = "hides a listing")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Successfully published listing"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Listing was not found",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDto.class))})})
    public ResponseEntity<Void> hideListing(@PathVariable("id") UUID id) throws EntityNotFoundException {
        listingService.hide(id);
        return ResponseEntity.noContent().build();
    }
}
