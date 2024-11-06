public class LineItem {
    private Product product;
    private int quantity;
    private int calculatedTotal;

    // Constructor for LineItem
    public LineItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.calculatedTotal = calculateTotal(); // Calculate total when creating LineItem
    }

    // Method to calculate total
    private int calculateTotal() {
        return product.getPrice() * quantity; // Total = price of product * quantity
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getCalculatedTotal() {
        return calculatedTotal;
    }
}
