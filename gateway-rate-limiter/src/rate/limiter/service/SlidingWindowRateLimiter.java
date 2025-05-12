package rate.limiter.service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import rate.limiter.algos.model.SlidingWindowServiceConfig;
import rate.limiter.algos.model.SlidingWindowState;
import rate.limiter.service.config.repos.ServiceConfigurationRepository;

public class SlidingWindowRateLimiter implements RateLimiterService {

    private final ServiceConfigurationRepository<SlidingWindowServiceConfig> serviceConfigRepository;
    private final ConcurrentMap<String, ConcurrentMap<String, SlidingWindowState>> serviceSlidingWindowMap;

    public SlidingWindowRateLimiter(
            ServiceConfigurationRepository<SlidingWindowServiceConfig> serviceConfigRepository) {
        this.serviceConfigRepository = serviceConfigRepository;
        this.serviceSlidingWindowMap = new ConcurrentHashMap<>();
    }

    @Override
    public boolean isRequestAllowed(String clientId, String serviceName) {
        SlidingWindowServiceConfig config = serviceConfigRepository.getConfiguration(serviceName);
        int windowSizeInMillis = config.windowSizeInMillis();
        int limit = config.limit();
        long currentTimeInMillis = System.currentTimeMillis();
        ConcurrentMap<String, SlidingWindowState> clientSlidingWindowMap = serviceSlidingWindowMap
                .computeIfAbsent(serviceName, k -> new ConcurrentHashMap<>());
        SlidingWindowState slidingWindowState = clientSlidingWindowMap
                .computeIfAbsent(clientId, k -> new SlidingWindowState(limit, windowSizeInMillis));
        return slidingWindowState.isAllowedAndRecord(currentTimeInMillis);
    }

}
