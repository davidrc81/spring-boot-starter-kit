package com.startedKitApp.startedKit;

import com.startedKitApp.startedKit.model.Producto;
import com.startedKitApp.startedKit.model.Role;
import com.startedKitApp.startedKit.model.User;
import com.startedKitApp.startedKit.repository.ProductoRepository;
import com.startedKitApp.startedKit.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class StartedKitApplication {

	public static void main(String[] args) {
		SpringApplication.run(StartedKitApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public CommandLineRunner initData(ProductoRepository productoRepository,
									  UserRepository userRepository,
									  PasswordEncoder passwordEncoder) {
		return args -> {
			// Carga de productos demo
			if (productoRepository.count() == 0) {
				productoRepository.save(new Producto("Monitor 24 pulgadas", 150000.0));
				productoRepository.save(new Producto("Teclado Mecánico", 45000.0));
				productoRepository.save(new Producto("Mouse Óptico", 18000.0));
				System.out.println(">>> Productos de prueba cargados correctamente <<<");
			}

			// Carga de usuarios iniciales demo
			if (!userRepository.existsByUsername("admin")) {
				userRepository.save(User.builder()
						.username("admin")
						.email("admin@starterkit.com")
						.password(passwordEncoder.encode("admin123"))
						.role(Role.ROLE_ADMIN)
						.enabled(true)
						.build());
			}

			if (!userRepository.existsByUsername("user")) {
				userRepository.save(User.builder()
						.username("user")
						.email("user@starterkit.com")
						.password(passwordEncoder.encode("user123"))
						.role(Role.ROLE_USER)
						.enabled(true)
						.build());
			}

			System.out.println(">>> Usuarios de prueba cargados (admin/admin123, user/user123) <<<");
		};
	}
}