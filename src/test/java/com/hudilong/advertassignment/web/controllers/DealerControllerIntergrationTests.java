package com.hudilong.advertassignment.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hudilong.advertassignment.TestUtility;
import com.hudilong.advertassignment.web.dtos.DealerDto;
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
public class DealerControllerIntergrationTests {

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    @Autowired
    public DealerControllerIntergrationTests(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    void testCreateDealer() throws Exception {
        DealerDto dealerDto = TestUtility.createDealerDto1();
        String jsonDealer = objectMapper.writeValueAsString(dealerDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/advertising/dealers/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonDealer)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNotEmpty()
        ).andExpect(MockMvcResultMatchers.jsonPath("$.name").value(dealerDto.name()));
    }
}
