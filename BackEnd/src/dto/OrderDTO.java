package dto;

import javafx.scene.control.ComboBox;

import java.util.ArrayList;

public class OrderDTO {
    private String orderId;
    private String customerId;
    private String orderDate;
    private String orderTime;
    private double cost;
    private double discount;
    /* Bellow instance variables are used to modify an order */
    private ArrayList<ItemDetailsDTO> items;
    private ComboBox<String> cmbOrderIds;

    public OrderDTO(String orderId, double cost, ArrayList<ItemDetailsDTO> items, ComboBox<String> cmbOrderIds) {
        this.orderId = orderId;
        this.cost = cost;
        this.items = items;
        this.cmbOrderIds = cmbOrderIds;
    }

    public OrderDTO() {
    }

    public OrderDTO(String orderId, String customerId, String orderDate, String orderTime, double cost,double discount, ArrayList<ItemDetailsDTO> items) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.cost = cost;
        this.items = items;
        this.discount=discount;
    }

    public ComboBox<String> getCmbOrderIds() {
        return cmbOrderIds;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public ArrayList<ItemDetailsDTO> getItems() {
        return items;
    }

    public void setItems(ArrayList<ItemDetailsDTO> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", cost=" + cost +
                ", items=" + items +
                '}';
    }
}
