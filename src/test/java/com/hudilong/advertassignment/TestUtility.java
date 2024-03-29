package com.hudilong.advertassignment;

import com.hudilong.advertassignment.domain.entities.DealerEntity;
import com.hudilong.advertassignment.domain.entities.ListingEntity;
import com.hudilong.advertassignment.domain.enums.State;
import com.hudilong.advertassignment.web.dtos.DealerDto;
import com.hudilong.advertassignment.web.dtos.ListingDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class TestUtility {

    public static DealerEntity createDealerEntity1() {
        return new DealerEntity(
                null, "Crimson Cars"
        );
    }

    public static DealerDto createDealerDto1() {
        return new DealerDto(
                null,
                "Crimson Cars");
    }

    public static DealerDto createDealerDto2() {
        return new DealerDto(
                 UUID.fromString("713c1c58-cc4f-45d2-a398-90cf988b51b2"),
                "Steve's Autos");
    }

    public static ListingEntity createListingEntity1() {
        return new ListingEntity(
//                UUID.fromString("df98776f-25f0-4e63-a1a6-08f68e6e3d25"),
                null,
                createDealerEntity1(),
                "Mazda 3",
                BigDecimal.valueOf(7000.00),
                LocalDateTime.now(),
                State.DRAFT);
    }

    public static ListingDto createListingDto1() {
        return new ListingDto(
//                UUID.fromString("df98776f-25f0-4e63-a1a6-08f68e6e3d25"),
                null,
                createDealerDto2(),
                "Mazda 3",
                BigDecimal.valueOf(7000.00),
                LocalDateTime.now(),
                State.DRAFT);
    }

    public static ListingDto createListingDto2() {
        return new ListingDto(
//                UUID.fromString("f2383681-f8b8-4e0c-a8dd-61406448f7c8"),
                null,
                createDealerDto2(),
                "Honda Civic",
                BigDecimal.valueOf(3000.00),
                LocalDateTime.now(),
                State.DRAFT);
    }

    public static ListingDto createListingDto3() {
        return new ListingDto(
//                UUID.fromString("0d45cc2f-8df4-4dd3-91b0-68ee4a73c67c"),
                null,
                createDealerDto2(),
                "Jeep Wrangler",
                BigDecimal.valueOf(23000.00),
                LocalDateTime.now(),
                State.DRAFT);
    }


}
