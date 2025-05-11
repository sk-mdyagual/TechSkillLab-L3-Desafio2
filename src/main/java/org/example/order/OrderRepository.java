package org.example.order;


import java.util.HashMap;
import java.util.Map;

public class OrderRepository {

    // Simula una base de datos en memoria
    private final Map<Integer,Order> database = new HashMap<>();
    private int currentId = 1;

    // Simular obtener una orden
    public Order findOrderById(int orderId) {
        return database.getOrDefault(orderId, null);
    }

    // Simular guardar una orden
    public int saveOrder(Order order) {
        database.put(currentId, order);
        return currentId++;
    }

    // Simular eliminar una orden
    public boolean deleteOrder(int orderId) {
        return database.remove(orderId) != null;
    }

    // Simular actualizar una orden
    public void updateOrder(int orderId, Order order) {
        if (database.containsKey(orderId)) {
            database.put(orderId, order);

        }
        else {
            throw new IllegalArgumentException("Orden no encontrada para actualizar");
        }
    }



}