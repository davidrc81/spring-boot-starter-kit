package com.startedKitApp.startedKit.controller;

import com.startedKitApp.startedKit.model.Producto;
import com.startedKitApp.startedKit.repository.ProductoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/productos")
@RequiredArgsConstructor
@Tag(name = "Productos", description = "Endpoints de gestión de productos de la API REST")
@SecurityRequirement(name = "bearerAuth")
public class ProductoController {

    private final ProductoRepository productoRepository;

    @Operation(summary = "Obtener listado paginado de productos", description = "Devuelve una página de productos con soporte para filtro por nombre.")
    @GetMapping
    public ResponseEntity<Page<Producto>> listarProductos(
            @RequestParam(value = "buscar", required = false) String buscar,
            Pageable pageable) {

        if (buscar != null && !buscar.isBlank()) {
            return ResponseEntity.ok(productoRepository.findByNombreContainingIgnoreCase(buscar, pageable));
        }
        return ResponseEntity.ok(productoRepository.findAll(pageable));
    }

    @Operation(summary = "Obtener producto por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id) {
        return productoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear nuevo producto")
    @PostMapping
    public ResponseEntity<Producto> crearProducto(@Valid @RequestBody Producto producto) {
        Producto nuevo = productoRepository.save(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @Operation(summary = "Actualizar producto existente")
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @Valid @RequestBody Producto productoDetalles) {
        return productoRepository.findById(id)
                .map(p -> {
                    p.setNombre(productoDetalles.getNombre());
                    p.setCategoria(productoDetalles.getCategoria());
                    p.setPrecio(productoDetalles.getPrecio());
                    p.setStock(productoDetalles.getStock());
                    p.setDescripcion(productoDetalles.getDescripcion());
                    p.setActivo(productoDetalles.getActivo());
                    return ResponseEntity.ok(productoRepository.save(p));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar producto por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
