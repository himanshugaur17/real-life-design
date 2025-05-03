package loyalty.program.models;

import java.time.LocalDate;

public record Point(String id, long points, int multiplier, LocalDate expirationDate) {
    public boolean isExpired(LocalDate timestamp) {
        return expirationDate.isBefore(timestamp);
    }

    public long redeem() {
        return points * multiplier;
    }
}
