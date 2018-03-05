package com.eu.gsys.wma.web.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration // tells the Spring Framework this is a Java configuration class
@EnableAutoConfiguration //This is what has Spring Boot automatically create the Spring Beans with sensible defaults for our tests.
@EntityScan(basePackages = {"com.eu.gsys.wma.web.domain.entities"})
@EnableJpaRepositories(basePackages = {"com.eu.gsys.wma.web.domain.repositories"}) // enables the auto configuration of Spring Data JPA
@EnableTransactionManagement // Enables Spring’s annotation driven transaction management
public class RepositoryConfiguration {
}