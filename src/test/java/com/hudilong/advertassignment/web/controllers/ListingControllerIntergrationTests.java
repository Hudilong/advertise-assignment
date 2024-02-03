package com.hudilong.advertassignment.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hudilong.advertassignment.TestUtility;
import com.hudilong.advertassignment.domain.entities.DealerEntity;
import com.hudilong.advertassignment.domain.entities.ListingEntity;
import com.hudilong.advertassignment.domain.services.DealerService;
import com.hudilong.advertassignment.persistence.repositories.DealerRepository;
import com.hudilong.advertassignment.web.dtos.DealerDto;
import com.hudilong.advertassignment.web.dtos.ListingDto;
import com.hudilong.advertassignment.web.mappers.DealerMapper;
import com.hudilong.advertassignment.web.mappers.ListingMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
@Log
public class ListingControllerIntergrationTests {

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    private final DealerRepository dealerRepository;

    private final ListingMapper listingMapper;

    @Setter
    @Getter
    private DealerEntity dealerEntity = null;

    @Autowired
    public ListingControllerIntergrationTests(MockMvc mockMvc, DealerRepository dealerRepository, ListingMapper listingMapper) {
        this.mockMvc = mockMvc;
        this.listingMapper = listingMapper;
        this.objectMapper = new ObjectMapper();
        this.dealerRepository = dealerRepository;
    }

    @BeforeEach
    void setup() {
        DealerEntity dealerEntity = TestUtility.createDealerEntity1();
        this.setDealerEntity(dealerRepository.save(dealerEntity));
    }

    @Test
    void testCreateListing() throws Exception {
        ListingEntity listingEntity = TestUtility.createListingEntity1();
        listingEntity.setDealer(dealerEntity);
        ListingDto listingDto = listingMapper.mapEntityToDto(listingEntity);

        String jsonListing = objectMapper.writeValueAsString(listingEntity);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/advertising/listings/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonListing)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNotEmpty()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.dealer").value(listingDto.dealer())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.vehicle").value(listingDto.vehicle())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.price").value(listingDto.price())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.state").value(listingDto.state())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.createdAt").isNotEmpty()
        );
    }

}
