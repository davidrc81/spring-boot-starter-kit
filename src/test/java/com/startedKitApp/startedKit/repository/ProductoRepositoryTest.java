package com.startedKitApp.startedKit.repository;

import com.startedKitApp.startedKit.model.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductoRepositoryTest {

    @Mock
    private ProductoRepository productoRepository;

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
    @DisplayName("Debe buscar productos por nombre ignorando mayúsculas y minúsculas")
    void debeBuscarPorNombreContainingIgnoreCase() {
        Page<Producto> paginaSimulada = new PageImpl<>(List.of(productoPrueba));
        when(productoRepository.findByNombreContainingIgnoreCase(eq("teclado"), any(PageRequest.class)))
                .thenReturn(paginaSimulada);

        Page<Producto> resultado = productoRepository.findByNombreContainingIgnoreCase("teclado", PageRequest.of(0, 10));

        assertThat(resultado.getContent()).hasSize(1);
        assertThat(resultado.getContent().get(0).getNombre()).isEqualTo("Teclado Mecánico RGB");
    }

    @Test
    @DisplayName("Debe guardar y recuperar un producto de la base de datos")
    void debeGuardarYBuscarPorId() {
        when(productoRepository.save(any(Producto.class))).thenReturn(productoPrueba);
        when(productoRepository.findById(1L)).thenReturn(Optional.of(productoPrueba));

        Producto guardado = productoRepository.save(productoPrueba);
        Optional<Producto> encontrado = productoRepository.findById(guardado.getId());

        assertThat(guardado.getId()).isNotNull();
        assertThat(encontrado).isPresent();
        assertThat(encontrado.get().getNombre()).isEqualTo("Teclado Mecánico RGB");
    }
}