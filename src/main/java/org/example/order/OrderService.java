package org.example.order;

public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public int processOrder(Order order) {
        if (order == null || order.getDetails() == null || order.getDetails().isBlank()) {
            throw new IllegalArgumentException("Detalles de la orden no pueden ser vacíos");
        }
        return orderRepository.saveOrder(order);
    }

    public Order retrieveOrder(int orderId) {
        Order order = orderRepository.findOrderById(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Orden no encontrada");
        }
        return order;
    }

    public void updateOrder(int orderId, Order order) {
        if (order == null || order.getDetails() == null || order.getDetails().isBlank()) {
            throw new IllegalArgumentException("Detalles de la orden no pueden ser vacíos");
        }
        if (orderId <= 0) {
            throw new IllegalArgumentException("ID de la orden no puede ser menor o igual a cero");
        }
        orderRepository.updateOrder(orderId, order);
    }
}
