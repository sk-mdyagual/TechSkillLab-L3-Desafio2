package org.example.order;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

// Anotaciones Mockito
/*
@Mock: Crea un objeto simulado respecto a una clase/interfaz
@InjectMocks: Crea una instancia de lo quese va a probar e inyecta los mocks mecesarios
@ExtendWith(MockitoExtension.class): Activa un soporte de MOckito para JUnit 5
 */

class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;


    private Order orderPrueba;
    private Order orderInvalida;
    private Order orderBlank;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Las otras líneas no son necesarias ya que openMocks abre los mocks de esta clase
        //orderRepository = Mockito.mock(OrderRepository.class);
        //orderService = new OrderService(orderRepository);
        orderPrueba = new Order(100, "Orden de prueba", "Cliente 1");
        orderInvalida = new Order(101, null, "Cliente 2");
        orderBlank = new Order(102, "", "Cliente 3");
    }

    @Test
    void processOrder_Success() {
        /*
        1. Simular comportamiento del repositorio
        2. Llamar al servicio relacionado
        3. Verifico resultado
        4. Verifico el # de llamados de métodos
         */
        // 1.
        Mockito.when(orderRepository.saveOrder(Mockito.any(Order.class)))
                .thenReturn(orderPrueba.getId());
        // 2.
        int orderId = orderService.processOrder(orderPrueba);
        // 3.
        Assertions.assertEquals(100, orderId);
        // 4.
        Mockito.verify(orderRepository, Mockito.times(1))
                .saveOrder(orderPrueba);
    }

    @Test
    void processOrderNull_or_Blank_Failed() {
        // 1.
        Mockito.when(orderRepository.saveOrder((Mockito.any(Order.class))))
                .thenThrow(new IllegalArgumentException("Detalles de la orden no pueden ser vacíos"));
        // 2 y 3
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            orderService.processOrder(orderInvalida);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            orderService.processOrder(orderBlank);
        });

        // 4.
        Mockito.verify(orderRepository, Mockito.never())
                .saveOrder(Mockito.any(Order.class));
    }

    @Test
    void processOrderNull_or_Blank_Failed2() {
        // Aquí solo pruebo el servicio, en el anterior, controlo el repositorio y el servicio
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            orderService.processOrder(orderInvalida);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            orderService.processOrder(orderBlank);
        });

        Mockito.verify(orderRepository, Mockito.never())
                .saveOrder(Mockito.any(Order.class));
    }

    @Test
    void retrieveOrder() {
    }

    @Test
    void updateOrder() {
    }
}