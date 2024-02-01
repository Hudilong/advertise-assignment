package com.hudilong.advertassignment.web.controllers;

import com.hudilong.advertassignment.domain.services.DealerService;
import com.hudilong.advertassignment.web.dtos.DealerDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/advertising/dealers")
public class DealerController {

    private final DealerService dealerService;

    public DealerController(DealerService dealerService) {
        this.dealerService = dealerService;
    }

    @PostMapping(path = "/")
    @Operation(description = "Creates a new dealer from the provided information.")
    @ApiResponse(
            responseCode = "201",
            description = "Dealer created",
            content = {@Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = DealerDto.class))})
    public ResponseEntity<DealerDto> createDealer(@RequestBody @Validated DealerDto dealerDto) {
        DealerDto savedDealer = dealerService.create(dealerDto);
        return new ResponseEntity<>(savedDealer, HttpStatus.CREATED);
    }


    @GetMapping(path = "/")
    @Operation(
            summary = "Get all dealers",
            description = "Retrieves a list of all dealers")
    @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved list of dealers",
            content = {@Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = DealerDto.class))})
    public ResponseEntity<List<DealerDto>> getAllDealers() {
        List<DealerDto> dealers = dealerService.findAll();
        return new ResponseEntity<>(dealers, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    @Operation(
            summary = "Delete a dealer",
            description = "Delete a dealer by id")
    @ApiResponse(
            responseCode = "204",
            description = "Successfully deleted dealer")
    public ResponseEntity<Void> deleteDealer(@PathVariable("id") UUID id) throws EntityNotFoundException {
        dealerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/")
    @Operation(
            summary = "Delete all dealers",
            description = "Delete all the dealer")
    @ApiResponse(
            responseCode = "204",
            description = "Successfully deleted all dealers")
    public ResponseEntity<Void> deleteAllDealers() {
        dealerService.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
