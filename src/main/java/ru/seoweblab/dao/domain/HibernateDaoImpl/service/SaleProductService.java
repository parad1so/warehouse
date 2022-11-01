package ru.seoweblab.dao.domain.HibernateDaoImpl.service;

import ru.seoweblab.dao.domain.HibernateDaoImpl.SaleProductHibDaoImpl;
import ru.seoweblab.model.SaleOfProduct;
import lombok.Data;

import java.util.List;


/**
 * КлассSaleProductService использует объект DAO для взаимодействия с базой данных.
 */
@Data
public class SaleProductService {

    private static  SaleProductHibDaoImpl product;
    public SaleProductService() {
        product = new SaleProductHibDaoImpl();
    }
    public void save(SaleOfProduct product) {
        getProduct().openCurrentSessionWithTransaction();
        getProduct().save(product);
        getProduct().closeCurrentSessionWithTransaction();
    }
    public void update(SaleOfProduct  product) {
        getProduct().openCurrentSessionWithTransaction();
        getProduct().update(product);
        getProduct().closeCurrentSessionWithTransaction();
    }
    public SaleOfProduct  findById(Integer id) {
        getProduct().openCurrentSession();
        final SaleOfProduct  product = getProduct().findById(id);
        getProduct().closeCurrentSession();
        return product;
    }
    public void delete(Integer id) {
        getProduct().openCurrentSessionWithTransaction();
        final SaleOfProduct  product = getProduct().findById(id);
        getProduct().delete(product);
        getProduct().closeCurrentSessionWithTransaction();
    }
    public List<SaleOfProduct > findAll() {
        getProduct().openCurrentSession();
        List<SaleOfProduct > list = getProduct().findAll();
        getProduct().closeCurrentSession();
        return list;
    }

    public  SaleProductHibDaoImpl  getProduct() {
        return product;
    }
}
