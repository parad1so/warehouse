package com.warehouse.service.spring;

import com.warehouse.model.ArrivalOfProduct;
import com.warehouse.model.MovingOfProduct;
import com.warehouse.model.SaleOfProduct;
import com.warehouse.view.interfaceView.View;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan(basePackages = "com.warehouse")
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
