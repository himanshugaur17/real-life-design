package loyalty.program.models;

import java.util.List;

public record Order(String id, Customer customer, double amount,
        List<OrderItem> items, double orderValue) {

}
