package rate.limiter.algos.model;

import java.util.concurrent.atomic.AtomicLong;

public class Window {
    private final long startTime;
    private final long windowSize;
    private final AtomicLong count = new AtomicLong(0);

    public Window(long startTime, long windowSize) {
        this.startTime = startTime;
        this.windowSize = windowSize;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getWindowSize() {
        return windowSize;
    }

    public long getCount() {
        return count.get();
    }

    public boolean isExpired(long currentTime) {
        return currentTime - startTime > windowSize;
    }

    public long incrementAndGet() {
        return count.incrementAndGet();
    }

    public void decrementAndGet() {
        count.decrementAndGet();
    }
}
