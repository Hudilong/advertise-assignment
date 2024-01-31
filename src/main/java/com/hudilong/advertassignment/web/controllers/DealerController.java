package com.hudilong.advertassignment.web.controllers;

import com.hudilong.advertassignment.domain.entities.DealerEntity;
import com.hudilong.advertassignment.domain.services.DealerService;
import com.hudilong.advertassignment.web.dtos.DealerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/advertising/dealers")
public class DealerController {

    private DealerService dealerService;

    public DealerController(DealerService dealerService) {
        this.dealerService = dealerService;
    }
    
    @PostMapping(path = "/")
    public ResponseEntity<DealerDto> createDealer(@RequestBody @Validated DealerDto dealerDto) {
        DealerDto savedDealer = dealerService.create(dealerDto);
        return new ResponseEntity<>(savedDealer, HttpStatus.CREATED);
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<DealerDto>> getAllDealers() {
        List<DealerDto> dealers = dealerService.findAll();
        return new ResponseEntity<>(dealers, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteDealer(@PathVariable("id") UUID id) {
        dealerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/")
    public ResponseEntity<Void> deleteAllDealers() {
        dealerService.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
