package rate.limiter.service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import rate.limiter.algos.model.TokenBucketServiceConfig;
import rate.limiter.algos.model.TokenBucketState;
import rate.limiter.service.config.repos.ServiceConfigurationRepository;

public class TokenBucketRateLimiterService implements RateLimiterService {

    private final ServiceConfigurationRepository<TokenBucketServiceConfig> serviceConfigRepository;
    /*
     * serviceName -> clientId -> tokenBucketState
     */
    private final ConcurrentMap<String, ConcurrentMap<String, TokenBucketState>> serviceTokenBucketMap;

    public TokenBucketRateLimiterService(
            ServiceConfigurationRepository<TokenBucketServiceConfig> serviceConfigRepository) {
        this.serviceConfigRepository = serviceConfigRepository;
        this.serviceTokenBucketMap = new ConcurrentHashMap<>();
    }

    @Override
    public boolean isRequestAllowed(String clientId, String serviceName) {
        TokenBucketServiceConfig config = serviceConfigRepository.getConfiguration(serviceName);
        int bucketSize = config.bucketSize();
        int refillAmount = config.refillAmount();
        long refillTimeInMillis = config.refillIntervalUnit().toMillis(config.refillInterval());
        long currentTimeInMillis = System.currentTimeMillis();
        ConcurrentMap<String, TokenBucketState> clientTokenBucketMap = serviceTokenBucketMap
                .computeIfAbsent(serviceName, k -> new ConcurrentHashMap<>());
        clientTokenBucketMap.computeIfAbsent(clientId,
                k -> new TokenBucketState(bucketSize, currentTimeInMillis));

        TokenBucketState tokenBucketState = clientTokenBucketMap.get(clientId);
        tokenBucketState.refill(bucketSize, refillAmount, refillTimeInMillis, currentTimeInMillis);
        return tokenBucketState.consumeToken();
    }
}
