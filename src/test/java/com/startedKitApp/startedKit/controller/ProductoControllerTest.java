package com.startedKitApp.startedKit.controller;

import com.startedKitApp.startedKit.model.Producto;
import com.startedKitApp.startedKit.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductoControllerTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoController productoController;

    private Producto productoPrueba;

    @BeforeEach
    void setUp() {
        productoPrueba = Producto.builder()
                .id(1L)
                .nombre("Teclado Mecánico RGB")
                .descripcion("Switches Red")
                .precio(new BigDecimal("45000.00"))
                .stock(10)
                .categoria("Periféricos")
                .activo(true)
                .createdAt(LocalDateTime.now())
                .build();
    }

    @Test
    @DisplayName("Debe listar productos paginados correctamente")
    void debeListarProductosPaginados() {
        Page<Producto> paginaSimulada = new PageImpl<>(List.of(productoPrueba));

        // Mockeamos ambas opciones posibles para garantizar que responda la pagina simulada
        lenient().when(productoRepository.findAll(any(Pageable.class))).thenReturn(paginaSimulada);
        lenient().when(productoRepository.findByNombreContainingIgnoreCase(any(), any())).thenReturn(paginaSimulada);

        Pageable pageable = PageRequest.of(0, 10);
        ResponseEntity<Page<Producto>> respuesta = productoController.listarProductos("", pageable);

        assertThat(respuesta.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(respuesta.getBody()).isNotNull();
        assertThat(respuesta.getBody().getContent()).hasSize(1);
        assertThat(respuesta.getBody().getContent().get(0).getNombre()).isEqualTo("Teclado Mecánico RGB");
    }

    @Test
    @DisplayName("Debe obtener un producto por ID cuando existe")
    void debeObtenerProductoPorId() {
        when(productoRepository.findById(1L)).thenReturn(Optional.of(productoPrueba));

        ResponseEntity<Producto> respuesta = productoController.obtenerPorId(1L);

        assertThat(respuesta.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(respuesta.getBody()).isNotNull();
        assertThat(respuesta.getBody().getNombre()).isEqualTo("Teclado Mecánico RGB");
    }
}