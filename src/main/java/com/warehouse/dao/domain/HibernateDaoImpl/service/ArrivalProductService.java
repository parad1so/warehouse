package com.warehouse.dao.domain.HibernateDaoImpl.service;

import com.warehouse.dao.domain.HibernateDaoImpl.ArrivalProductHibDaoImpl;
import com.warehouse.model.ArrivalOfProduct;
import lombok.Data;

import java.util.List;

/**
 * Класс ArrivalProductService использует объект DAO для взаимодействия с базой данных.
 */
@Data
public class ArrivalProductService {
    private static ArrivalProductHibDaoImpl product;
    public ArrivalProductService() {
        product = new ArrivalProductHibDaoImpl();
    }
    public void save( ArrivalOfProduct product) {
    getProduct().openCurrentSessionWithTransaction();
    getProduct().save(product);
    getProduct().closeCurrentSessionWithTransaction();
    }
    public void update(ArrivalOfProduct product) {
     getProduct().openCurrentSessionWithTransaction();
     getProduct().update(product);
     getProduct().closeCurrentSessionWithTransaction();
    }
    public ArrivalOfProduct findById(Integer id) {
        getProduct().openCurrentSession();
       final ArrivalOfProduct product = getProduct().findById(id);
        getProduct().closeCurrentSession();
        return product;
    }
    public void delete(Integer id) {
        getProduct().openCurrentSessionWithTransaction();
        final ArrivalOfProduct product = getProduct().findById(id);
        getProduct().delete(product);
        getProduct().closeCurrentSessionWithTransaction();
    }
    public List<ArrivalOfProduct> findAll() {
        getProduct().openCurrentSession();
        List<ArrivalOfProduct> list = getProduct().findAll();
        getProduct().closeCurrentSession();
        return list;
    }

    public ArrivalProductHibDaoImpl getProduct() {
        return product;
    }
}
