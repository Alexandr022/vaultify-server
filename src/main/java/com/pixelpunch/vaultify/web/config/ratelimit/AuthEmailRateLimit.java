package com.pixelpunch.vaultify.web.config.ratelimit;


import java.time.Duration;

public class AuthEmailRateLimit extends BaseRateLimit {
    public AuthEmailRateLimit(long capacity, long refill, Duration refillDuration) {
        super(capacity, refill, refillDuration);
    }

    @Override
    public long getCapacity() {
        return 2;
    }

    @Override
    public long getRefill() {
        return 1;
    }

    @Override
    public Duration getRefillDuration() {
        return Duration.ofMinutes(5);
    }
}
