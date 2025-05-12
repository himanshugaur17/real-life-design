package rate.limiter.algos.model;

public record SlidingWindowServiceConfig(int windowSizeInMillis, int limit) {

}
