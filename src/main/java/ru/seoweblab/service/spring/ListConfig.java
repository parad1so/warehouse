package ru.seoweblab.service.spring;

import ru.seoweblab.model.ArrivalOfProduct;
import ru.seoweblab.model.MovingOfProduct;
import ru.seoweblab.model.SaleOfProduct;
import ru.seoweblab.view.interfaceView.View;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan(basePackages = "ru.seoweblab")
public class ListConfig {


    @Bean
    public List<ArrivalOfProduct> arrivalOfProducts() {
        return new ArrayList<>();
    }
    @Bean
    public List<SaleOfProduct> saleOfProducts() {
        return new ArrayList<>();
    }
    @Bean
    public List<MovingOfProduct> movingOfProducts() {
        return new ArrayList<>();
    }
    @Bean
    public List<View> views() {
        return new ArrayList<>();
    }


}
