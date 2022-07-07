package entity;


public class Order {
    private String orderID;
    private String orderDate;
    private String custID;
    private double price;

    public Order() {
    }

    public Order(String orderID, String orderDate, String custID, double price) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.custID = custID;
        this.price = price;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
