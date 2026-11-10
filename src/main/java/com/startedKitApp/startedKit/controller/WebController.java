package com.startedKitApp.startedKit.controller;

import com.startedKitApp.startedKit.model.Producto;
import com.startedKitApp.startedKit.repository.ProductoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final ProductoRepository productoRepository;

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/admin/dashboard")
    public String dashboard(@RequestParam(value = "buscar", required = false) String buscar,
                            @RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "5") int size,
                            Model model) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Producto> paginaProductos;

        if (buscar != null && !buscar.isBlank()) {
            paginaProductos = productoRepository.findByNombreContainingIgnoreCase(buscar, pageable);
        } else {
            paginaProductos = productoRepository.findAll(pageable);
        }

        if (!model.containsAttribute("nuevoProducto")) {
            model.addAttribute("nuevoProducto", new Producto());
        }

        model.addAttribute("productos", paginaProductos.getContent());
        model.addAttribute("paginaActual", page);
        model.addAttribute("totalPaginas", paginaProductos.getTotalPages());
        model.addAttribute("totalElementos", paginaProductos.getTotalElements());
        model.addAttribute("buscar", buscar);

        return "admin/dashboard";
    }

    // Endpoint de Autocompletado en tiempo real (devuelve JSON)
    @GetMapping("/admin/productos/buscar-sugerencias")
    @ResponseBody
    public List<Producto> buscarSugerencias(@RequestParam("term") String term) {
        if (term == null || term.trim().length() < 2) {
            return Collections.emptyList();
        }
        Pageable limit = PageRequest.of(0, 5, Sort.by("nombre").ascending());
        return productoRepository.findByNombreContainingIgnoreCase(term, limit).getContent();
    }

    @PostMapping("/admin/productos/guardar")
    public String guardarProducto(@Valid @ModelAttribute("nuevoProducto") Producto producto,
                                  BindingResult bindingResult,
                                  Model model,
                                  RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            Pageable pageable = PageRequest.of(0, 5, Sort.by("id").descending());
            Page<Producto> paginaProductos = productoRepository.findAll(pageable);
            model.addAttribute("productos", paginaProductos.getContent());
            model.addAttribute("paginaActual", 0);
            model.addAttribute("totalPaginas", paginaProductos.getTotalPages());
            model.addAttribute("totalElementos", paginaProductos.getTotalElements());
            model.addAttribute("org.springframework.validation.BindingResult.nuevoProducto", bindingResult);
            return "admin/dashboard";
        }
        productoRepository.save(producto);
        redirectAttributes.addFlashAttribute("mensajeÉxito", "Producto guardado correctamente.");
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/admin/productos/actualizar")
    public String actualizarProducto(@Valid @ModelAttribute Producto producto,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("mensajeError", "No se pudo actualizar: revise los datos ingresados.");
            return "redirect:/admin/dashboard";
        }
        productoRepository.save(producto);
        redirectAttributes.addFlashAttribute("mensajeÉxito", "Producto actualizado con éxito.");
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/admin/productos/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        productoRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensajeÉxito", "Producto eliminado del sistema.");
        return "redirect:/admin/dashboard";
    }
}