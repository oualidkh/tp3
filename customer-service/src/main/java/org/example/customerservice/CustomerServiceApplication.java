package org.example.customerservice;

import org.aspectj.apache.bcel.Repository;
import org.example.customerservice.entities.Customer;
import org.example.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(CustomerRepository CustomerRepository, RepositoryRestConfiguration restConfiguration)
    {
        return  args -> {
            restConfiguration.exposeIdsFor(Customer.class);
            CustomerRepository.saveAll(
                    List.of(
                            Customer.builder().name("Ouail").email("ouail@gmail.com").build(),
                            Customer.builder().name("Hassan").email("Hassan@gmail.com").build(),
                            Customer.builder().name("Mohammed").email("Mohammed@gmail.com").build()
                    )
            );
            CustomerRepository.findAll().forEach(
                    customer -> System.out.println(customer)
            );


        };

    }

}
