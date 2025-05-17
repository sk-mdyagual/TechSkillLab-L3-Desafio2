package org.example;

import org.example.order.Order;
import org.example.order.OrderRepository;
import org.example.order.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

class OrderServiceTest {
    private OrderRepository orderRepository;
    private OrderService orderService;
    private Order orderPrueba;
    private Order orderInvalida;
    private Order orderBlank;

    @BeforeEach
    void setUp(){
        orderRepository= Mockito.mock(OrderRepository.class);
        orderService = new OrderService(orderRepository);
        orderPrueba = new Order(100,"Orden de prueba","Cliente 1");
        orderInvalida = new Order(101,null,"cliente 2");
        orderBlank = new Order(102, "","Cliente 3");

    }

    @Test
    void processOrder_Success(){
        Mockito.when(orderRepository.saveOrder(Mockito.any(Order.class)))
                .thenReturn(orderPrueba.getId());

        int orderId = orderService.processOrder(orderPrueba);

        Assertions.assertEquals(100,orderId);

        Mockito.verify(orderRepository, Mockito.times(1))
                .saveOrder(orderPrueba);
    }

    @Test
    void processOrderNullOrBlank_Failed(){
        Mockito.when(orderRepository.saveOrder(Mockito.any(Order.class)))
                .thenThrow(new IllegalArgumentException("Los detalles no pueden ser vacios"));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            orderService.processOrder(orderInvalida);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            orderService.processOrder(orderBlank);
        });

        Mockito.verify(orderRepository, Mockito.never())
                .saveOrder(Mockito.any(Order.class));
    }
}
