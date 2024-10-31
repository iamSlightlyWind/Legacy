package fa.training.entities;

public class Order {
    private int orderId;
    private String orderDate;
    private int customerId;
    private int employeeId;
    private double total;

    public Order() {
    }

    public Order(int orderId, String orderDate, int customerId, int employeeId, double total) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.total = total;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public double getTotal() {
        return total;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
