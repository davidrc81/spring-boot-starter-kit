package com.startedKitApp.startedKit.service;

import com.startedKitApp.startedKit.common.dto.PageResponse;
import com.startedKitApp.startedKit.model.Producto;
import com.startedKitApp.startedKit.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;

    public PageResponse<Producto> getAllProductos(int page, int size, String sortBy, String sortDir, String search) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Producto> productos;
        if (search != null && !search.trim().isEmpty()) {
            productos = productoRepository.findByNombreContainingIgnoreCase(search, pageable);
        } else {
            productos = productoRepository.findAll(pageable);
        }

        return PageResponse.fromPage(productos);
    }
}
