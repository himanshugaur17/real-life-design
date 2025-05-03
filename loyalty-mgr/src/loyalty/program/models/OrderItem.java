package loyalty.program.models;

public record OrderItem(String id, String name, double price, int quantity, boolean isPromotional) {

    public double getItemValue() {
        return price * quantity;
    }

}
