package loyalty.program.repos;

import java.time.LocalDate;

public interface OrderRepository {
    long countOrdersBetweenDates(LocalDate startDate, LocalDate endDate);
}
