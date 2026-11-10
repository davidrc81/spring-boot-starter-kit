package com.startedKitApp.startedKit;

import com.startedKitApp.startedKit.model.Producto;
import com.startedKitApp.startedKit.model.User;
import com.startedKitApp.startedKit.model.Role;
import com.startedKitApp.startedKit.repository.ProductoRepository;
import com.startedKitApp.startedKit.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;

@SpringBootApplication
public class StartedKitApplication {

	public static void main(String[] args) {
		SpringApplication.run(StartedKitApplication.class, args);
	}

	@Bean
	CommandLineRunner initData(ProductoRepository productoRepository,
							   UserRepository userRepository,
							   PasswordEncoder passwordEncoder) {
		return args -> {

			// Carga inicial de productos estructurados
			if (productoRepository.count() == 0) {
				productoRepository.save(Producto.builder()
						.nombre("Monitor 24 pulgadas")
						.descripcion("Panel IPS Full HD 75Hz")
						.precio(new BigDecimal("140000.00"))
						.stock(15)
						.categoria("Periféricos")
						.activo(true)
						.build());

				productoRepository.save(Producto.builder()
						.nombre("Teclado Mecánico")
						.descripcion("Switch Blue retroiluminado RGB")
						.precio(new BigDecimal("45000.00"))
						.stock(30)
						.categoria("Periféricos")
						.activo(true)
						.build());

				productoRepository.save(Producto.builder()
						.nombre("Mouse Óptico")
						.descripcion("Sensor 3200 DPI USB")
						.precio(new BigDecimal("18000.00"))
						.stock(50)
						.categoria("Periféricos")
						.activo(true)
						.build());

				System.out.println(">>> Productos de prueba cargados correctamente <<<");
			}

			// Carga inicial de usuarios
			if (userRepository.count() == 0) {
				User admin = User.builder()
						.username("admin")
						.password(passwordEncoder.encode("admin123"))
						.email("admin@starterkit.com")
						.role(Role.ROLE_ADMIN)
						.enabled(true)
						.build();

				User user = User.builder()
						.username("user")
						.password(passwordEncoder.encode("user123"))
						.email("user@starterkit.com")
						.role(Role.ROLE_USER)
						.enabled(true)
						.build();

				userRepository.save(admin);
				userRepository.save(user);

				System.out.println(">>> Usuarios de prueba cargados (admin/admin123, user/user123) <<<");
			}
		};
	}
}