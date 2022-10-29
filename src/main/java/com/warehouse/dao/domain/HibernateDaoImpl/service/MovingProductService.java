package com.warehouse.dao.domain.HibernateDaoImpl.service;

import com.warehouse.dao.domain.HibernateDaoImpl.MovingProductHibDaoImpl;
import com.warehouse.dao.domain.HibernateDaoImpl.SaleProductHibDaoImpl;
import com.warehouse.model.MovingOfProduct;
import com.warehouse.model.SaleOfProduct;
import lombok.Data;

import java.util.List;

/**
 * Класс MovingProductService использует объект DAO для взаимодействия с базой данных.
 */
@Data
public class MovingProductService {

    private static MovingProductHibDaoImpl product;
    public MovingProductService() {
        product = new  MovingProductHibDaoImpl();
    }
    public void save(MovingOfProduct product) {
        getProduct().openCurrentSessionWithTransaction();
        getProduct().save(product);
        getProduct().closeCurrentSessionWithTransaction();
    }
    public void update(MovingOfProduct product) {
        getProduct().openCurrentSessionWithTransaction();
        getProduct().update(product);
        getProduct().closeCurrentSessionWithTransaction();
    }
    public MovingOfProduct findById(Integer id) {
        getProduct().openCurrentSession();
        final MovingOfProduct  product = getProduct().findById(id);
        getProduct().closeCurrentSession();
        return product;
    }
    public void delete(Integer id) {
        getProduct().openCurrentSessionWithTransaction();
        final MovingOfProduct  product = getProduct().findById(id);
        getProduct().delete(product);
        getProduct().closeCurrentSessionWithTransaction();
    }
    public List<MovingOfProduct> findAll() {
        getProduct().openCurrentSession();
        List<MovingOfProduct> list = getProduct().findAll();
        getProduct().closeCurrentSession();
        return list;
    }

    public   MovingProductHibDaoImpl  getProduct() {
        return product;
    }
}
