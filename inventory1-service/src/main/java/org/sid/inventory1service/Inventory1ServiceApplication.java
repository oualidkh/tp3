package org.sid.inventory1service;

import org.sid.inventory1service.entities.Product;
import org.sid.inventory1service.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;

@SpringBootApplication
public class Inventory1ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(Inventory1ServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(ProductRepository productRepository, RepositoryRestConfiguration restConfiguration)
    {
        return args -> {
            restConfiguration.exposeIdsFor(Product.class);
            productRepository.saveAll(
                    List.of(
                            Product.builder().name("Computer").quantity(12).price(12000).build(),
                            Product.builder().name("Printer").quantity(32).price(350).build(),
                            Product.builder().name("Smartphone").quantity(31).price(900).build()
                    )
            );
            productRepository.findAll().forEach(System.out::println);
        };
    }
}
