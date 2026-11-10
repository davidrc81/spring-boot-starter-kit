package com.startedKitApp.startedKit.controller;

import com.startedKitApp.startedKit.model.Producto;
import com.startedKitApp.startedKit.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final ProductoRepository productoRepository;

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/admin/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("productos", productoRepository.findAll());
        model.addAttribute("nuevoProducto", new Producto());
        return "admin/dashboard";
    }

    @PostMapping("/admin/productos/guardar")
    public String guardarProducto(@ModelAttribute("nuevoProducto") Producto producto) {
        productoRepository.save(producto);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/admin/productos/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        productoRepository.deleteById(id);
        return "redirect:/admin/dashboard";
    }
}