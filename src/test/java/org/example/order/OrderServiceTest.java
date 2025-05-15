package org.example.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OrderServiceTest {

    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    private Order orderPrueba;
    private Order orderBlanck;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks
        orderService = new OrderService(orderRepository);
        orderPrueba = new Order(100, "Detalles de la orden", "cliente 1");
        orderBlanck = new Order(102, "", "cliente 3");
    }

    @Test
    void testProcessOrder_ValidOrder() {
        // Configura el comportamiento del mock
        when(orderRepository.saveOrder(any(Order.class))).thenReturn(orderPrueba.getId());

        // Llama al método a probar
        int result = orderService.processOrder(orderPrueba);

        // Verifica el resultado
        assertEquals(100, result);

        // Verifica que el método del mock fue llamado con el argumento correcto
        verify(orderRepository).saveOrder(orderPrueba);
    }

    @Test
    void testProcessOrder_NullOrder() {
        // Verifica que se lance IllegalArgumentException cuando la orden es nula
        IllegalArgumentException exception =
                org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
                    orderService.processOrder(null);
                });
        assertEquals("Detalles de la orden no pueden ser vacíos", exception.getMessage());
    }

    @Test
    void testProcessOrder_BlankDetails() {
        // Verifica que se lance IllegalArgumentException cuando los detalles están en blanco
        IllegalArgumentException exception =
                org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
                    orderService.processOrder(orderBlanck);
                });
        assertEquals("Detalles de la orden no pueden ser vacíos", exception.getMessage());
    }
}

