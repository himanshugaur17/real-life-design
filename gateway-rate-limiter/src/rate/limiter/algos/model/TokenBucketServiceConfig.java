package rate.limiter.algos.model;

import java.util.concurrent.TimeUnit;

public record TokenBucketServiceConfig(int bucketSize, int refillAmount, int refillInterval,
        TimeUnit refillIntervalUnit) {

}
