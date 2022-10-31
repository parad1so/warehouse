package com.warehouse.dao.domain.documentsDaoHibernate;



import com.warehouse.dao.hibernateDao.CrudHibernateDao;
import com.warehouse.model.SaleOfProduct;

import java.util.List;

public interface DocumentsSaleHibDao extends CrudHibernateDao<SaleOfProduct, Integer> {
    List<SaleOfProduct> findAll();
    SaleOfProduct findById(Integer id);
}
