package rate.limiter.algos.model;

import java.util.concurrent.TimeUnit;

public record FixedWindowServiceConfig(int windowSize, int maxRequests, TimeUnit timeUnit) {
   
}
