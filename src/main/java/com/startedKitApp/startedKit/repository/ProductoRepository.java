package com.startedKitApp.startedKit.repository;

import com.startedKitApp.startedKit.model.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Page<Producto> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
}