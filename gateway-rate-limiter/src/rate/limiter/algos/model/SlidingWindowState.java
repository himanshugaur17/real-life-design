package rate.limiter.algos.model;

import java.util.HashSet;
import java.util.Set;

public class SlidingWindowState {
    private final Set<Long> window;
    private final int limit;
    private final long windowSizeInMillis;

    public SlidingWindowState(int limit, long windowSizeInMillis) {
        this.window = new HashSet<>();
        this.limit = limit;
        this.windowSizeInMillis = windowSizeInMillis;
    }

    public synchronized boolean isAllowedAndRecord(long currentTimeInMillis) {
        long windowStartTime = currentTimeInMillis - windowSizeInMillis;
        window.removeIf(time -> time < windowStartTime);
        if (window.size() >= limit) {
            return false;
        }
        window.add(currentTimeInMillis);
        return true;
    }
}
