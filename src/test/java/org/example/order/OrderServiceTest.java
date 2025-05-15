package org.example.order;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private OrderService orderService;

    private Order testOrder;
    private Order invalidOrder;
    private Order blankOrder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testOrder = new Order(100, "Test Order", "Client 1");
        invalidOrder = new Order(101, null, "Client 2");
        blankOrder = new Order(102, "", "Client 3");
    }

    @Test
    void processOrderSuccess() {
        Mockito.when(orderRepository.saveOrder(Mockito.any(Order.class)))
                .thenReturn(testOrder.getId());
        int orderId = orderService.processOrder(testOrder);
        assertEquals(testOrder.getId(), orderId);
        Mockito.verify(orderRepository, Mockito.times(1))
                .saveOrder(testOrder);
    }

    @Test
    void processOrderNullOrBlankThrowError() {
        Mockito.when(orderRepository.saveOrder(Mockito.any(Order.class)))
                .thenThrow(new IllegalArgumentException("Detalles de la orden no pueden ser vacíos"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            orderService.processOrder(invalidOrder);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            orderService.processOrder(blankOrder);
        });

        Mockito.verify(orderRepository, Mockito.never()).saveOrder(invalidOrder);

    }

    @Test
    void retrieveOrder() {
        Mockito.when(orderRepository.findOrderById(testOrder.getId()))
                .thenReturn(testOrder);
        Order retrievedOrder = orderService.retrieveOrder(testOrder.getId());
        assertEquals(testOrder, retrievedOrder);
        Mockito.verify(orderRepository, Mockito.times(1))
                .findOrderById(testOrder.getId());
    }

    @Test
    void updateOrder() {
        Mockito.doNothing().when(orderRepository)
                .updateOrder(Mockito.anyInt(), Mockito.any(Order.class));
        orderService.updateOrder(testOrder.getId(), testOrder);
        Mockito.verify(orderRepository, Mockito.times(1))
                .updateOrder(testOrder.getId(), testOrder);
    }
}