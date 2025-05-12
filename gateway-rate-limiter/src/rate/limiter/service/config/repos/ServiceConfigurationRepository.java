package rate.limiter.service.config.repos;

public interface ServiceConfigurationRepository<T> {
    T getConfiguration(String serviceName);
}
