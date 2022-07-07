package dto;

public class ItemDetailsDTO {
    private String itemCode;
    private String orderId;
    private double unitPrice;
    private double cost;
    private int qtyForSell;


    public ItemDetailsDTO() {
    }

    public ItemDetailsDTO( String itemCode,String orderId, double unitPrice, int qtyForSell,double cost) {

        this.itemCode = itemCode;
        this.orderId = orderId;
        this.unitPrice = unitPrice;
        this.qtyForSell = qtyForSell;
        this.cost = cost;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQtyForSell() {
        return qtyForSell;
    }

    public void setQtyForSell(int qtyForSell) {
        this.qtyForSell = qtyForSell;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "ItemDetails{" +
                "itemCode='" + itemCode + '\'' +
                ", unitPrice=" + unitPrice +
                ", qtyForSell=" + qtyForSell +
                '}';
    }
}
