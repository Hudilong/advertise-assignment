package com.hudilong.advertassignment.domain.exceptions;

import java.util.UUID;

public class TierLimitReachedException extends RuntimeException{

    public TierLimitReachedException(UUID dealerId) {
        super(String.join(" ", "The tier limit has been reached for dealer with id:", dealerId.toString()));
    }
}
