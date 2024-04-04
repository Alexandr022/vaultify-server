package com.pixelpunch.vaultify.web.config.ratelimit;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class BaseRateLimit {
    private final long capacity;
    private final long refill;
    private final Duration refillDuration;
    private final Map<String, RateLimitState> rateLimitStates;

    public BaseRateLimit(long capacity, long refill, Duration refillDuration) {
        this.capacity = capacity;
        this.refill = refill;
        this.refillDuration = refillDuration;
        this.rateLimitStates = new HashMap<>();
    }

    public boolean tryConsume(String key) {
        RateLimitState state = rateLimitStates.computeIfAbsent(key, k -> new RateLimitState(capacity, Instant.now()));
        synchronized (state) {
            refillTokens(state);
            if (state.tokens > 0) {
                state.tokens--;
                return true;
            } else {
                return false;
            }
        }
    }

    private void refillTokens(RateLimitState state) {
        Instant now = Instant.now();
        long elapsedTimeSeconds = Duration.between(state.lastRefill, now).getSeconds();
        long tokensToAdd = elapsedTimeSeconds * refill / refillDuration.getSeconds();
        state.tokens = Math.min(capacity, state.tokens + tokensToAdd);
        state.lastRefill = now;
    }

    public abstract long getCapacity();

    public abstract long getRefill();

    public abstract Duration getRefillDuration();

    private static class RateLimitState {
        private long tokens;
        private Instant lastRefill;

        private RateLimitState(long tokens, Instant lastRefill) {
            this.tokens = tokens;
            this.lastRefill = lastRefill;
        }
    }
}

