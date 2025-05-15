package org.example.order;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class OrderServiceTest {

    private OrderRepository orderRepository;
    private OrderService orderService;
    private Order orderPrueba;
    private Order orderInvalida;
    private Order orderVacia;

    @BeforeEach
    void setUp() {
        orderRepository = Mockito.mock(OrderRepository.class);
        orderService = new OrderService(orderRepository);
        orderPrueba = new Order(100, "Orden de prueba", "Cliente 1");
        orderInvalida = new Order(101, null, "Cliente 2");
        orderVacia = new Order(102, "", "Cliente 3");
    }


    @Test
    void process_order_guarda_y_devuelve_id() {
        Mockito.when(orderRepository.saveOrder(Mockito.any(Order.class)))
                .thenReturn(orderPrueba.getId());
        int orderId = orderService.processOrder(orderPrueba);
        Assertions.assertEquals(100, orderId);
        Mockito.verify(orderRepository, Mockito.times(1))
                .saveOrder(orderPrueba);
    }

    @Test
    void process_order_devuelve_excepcion_por_vacio_o_nulo() {
        Mockito.when(orderRepository.saveOrder(Mockito.any(Order.class)))
                .thenThrow(new IllegalArgumentException("El detalle de la orden no puede ser vacio o nulo."));
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            orderService.processOrder(orderInvalida);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            orderService.processOrder(orderVacia);
        });
        Mockito.verify(orderRepository, Mockito.never())
                .saveOrder(Mockito.any(Order.class));
    }

}