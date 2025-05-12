package rate.limiter.algos.model;

import java.util.concurrent.atomic.AtomicInteger;

public class TokenBucketState {
    private AtomicInteger currentTokens;
    private long lastRefillTime;

    public TokenBucketState(int currentTokens, long lastRefillTime) {
        this.currentTokens = new AtomicInteger(currentTokens);
        this.lastRefillTime = lastRefillTime;
    }

    public int getCurrentTokens() {
        return currentTokens.get();
    }

    public long getLastRefillTime() {
        return lastRefillTime;
    }

    public synchronized void refill(int bucketSize, int refillAmount, long refillTimeInMillis,
            long currentTimeInMillis) {
        long timeSinceLastRefill = currentTimeInMillis - lastRefillTime;
        long refillWindows = timeSinceLastRefill / refillTimeInMillis;
        if (refillWindows > 0) {
            currentTokens.getAndUpdate(prev -> Math.min(bucketSize, prev + (int) (refillAmount * refillWindows)));
            lastRefillTime = currentTimeInMillis;
        }
    }

    public boolean consumeToken() {
        boolean isConsumed = currentTokens.decrementAndGet() >= 0;
        if (!isConsumed) {
            currentTokens.incrementAndGet();
        }
        return isConsumed;
    }
}
