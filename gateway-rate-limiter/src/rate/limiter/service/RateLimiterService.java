package rate.limiter.service;

public interface RateLimiterService {
    boolean isRequestAllowed(String clientId, String serviceName);
}
