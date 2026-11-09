package com.startedKitApp.startedKit.controller;

import com.startedKitApp.startedKit.common.dto.PageResponse;
import com.startedKitApp.startedKit.model.Producto;
import com.startedKitApp.startedKit.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<PageResponse<Producto>> getAllProductos(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size,
            @RequestParam(defaultValue = "id", required = false) String sortBy,
            @RequestParam(defaultValue = "asc", required = false) String sortDir,
            @RequestParam(required = false) String search
    ) {
        PageResponse<Producto> response = productoService.getAllProductos(page, size, sortBy, sortDir, search);
        return ResponseEntity.ok(response);
    }
}
