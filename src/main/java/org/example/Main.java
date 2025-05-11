package org.example;

import org.example.order.Order;
import org.example.order.OrderRepository;
import org.example.order.OrderService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Crear una instancia de OrderRepository
        OrderRepository orderRepository = new OrderRepository();

        // Crear una instancia de OrderService inyectando OrderRepository
        OrderService orderService = new OrderService(orderRepository);

        //Crear órdenes
        for (int i = 200; i < 210; i++) {
            Order order = new Order(i, "Orden de prueba " + i, "Cliente " + i);
            // Llamar a un método del servicio
            orderService.processOrder(order);
        }

        // Llamar a un método del repositorio
        Order orderFound = orderRepository.findOrderById(207);
        System.out.println(orderFound);

        //Actualizar orden
        Order orderToUpdate = new Order(207, true);
        orderService.updateOrder(207, orderToUpdate);
    }
}