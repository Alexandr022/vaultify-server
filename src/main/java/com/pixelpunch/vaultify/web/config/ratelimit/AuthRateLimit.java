package com.pixelpunch.vaultify.web.config.ratelimit;

import java.time.Duration;

public class AuthRateLimit extends BaseRateLimit {

    public AuthRateLimit(long capacity, long refill, Duration refillDuration) {
        super(capacity, refill, refillDuration);
    }

    @Override
    public long getCapacity() {
        return 20;
    }

    @Override
    public long getRefill() {
        return 10;
    }

    @Override
    public Duration getRefillDuration() {
        return Duration.ofMinutes(1);
    }
}


