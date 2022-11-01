package ru.seoweblab.warehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "ru.seoweblab")
@EnableJpaRepositories(basePackages = "ru.seoweblab.dao.spring")
@EntityScan(basePackages = "ru.seoweblab.model")
public class StockInventoryApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(StockInventoryApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(StockInventoryApplication.class);
    }
}
