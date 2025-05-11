package org.example.order;

public class Order {
    private int id;
    private String details;
    private String cliente;
    private Boolean isDelivered;

    public Order(int id, String details, String cliente) {
        this.id = id;
        this.details = details;
        this.cliente = cliente;
        this.isDelivered = false;
    }

    public Order(int id, Boolean isDelivered) {
        this.id = id;
        this.isDelivered = isDelivered;
    }

    public int getId() {
        return id;
    }

    public String getDetails() {
        return details;
    }

    public String getCliente() {
        return cliente;
    }

    public Boolean getProcessed() {
        return isDelivered;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", details='" + details + '\'' +
                ", cliente='" + cliente + '\'' +
                ", isProcessed=" + isDelivered+
                '}';
    }
}
