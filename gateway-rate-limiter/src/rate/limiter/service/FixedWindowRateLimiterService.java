package rate.limiter.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import rate.limiter.algos.model.FixedWindowServiceConfig;
import rate.limiter.algos.model.Window;
import rate.limiter.service.config.repos.ServiceConfigurationRepository;

public class FixedWindowRateLimiterService implements RateLimiterService {
    private final ServiceConfigurationRepository<FixedWindowServiceConfig> serviceConfigRepository;
    /*
     * serviceName -> clientId -> window
     */
    private final Map<String, ConcurrentMap<String, Window>> serviceWindowMap;

    public FixedWindowRateLimiterService(
            ServiceConfigurationRepository<FixedWindowServiceConfig> serviceConfigRepository) {
        this.serviceConfigRepository = serviceConfigRepository;
        this.serviceWindowMap = new ConcurrentHashMap<>();
    }

    @Override
    public boolean isRequestAllowed(String clientId, String serviceName) {
        FixedWindowServiceConfig config = serviceConfigRepository.getConfiguration(serviceName);
        long maxRequests = config.maxRequests();
        long currentTime = System.currentTimeMillis();
        serviceWindowMap.computeIfAbsent(serviceName, k -> new ConcurrentHashMap<>());
        ConcurrentMap<String, Window> clientWindowMap = serviceWindowMap.get(serviceName);
        clientWindowMap.computeIfAbsent(clientId,
                k -> new Window(currentTime, config.timeUnit().toMillis(config.windowSize())));

        Window window = clientWindowMap.compute(clientId, (k, w) -> {
            if (w == null) {
                return new Window(currentTime, config.timeUnit().toMillis(config.windowSize()));
            } else if (w.isExpired(currentTime)) {
                return new Window(currentTime, config.timeUnit().toMillis(config.windowSize()));
            }
            return w;
        });

        long count = window.incrementAndGet();
        if (count > maxRequests) {
            window.decrementAndGet();
            return false;
        }
        return true;
    }

}
