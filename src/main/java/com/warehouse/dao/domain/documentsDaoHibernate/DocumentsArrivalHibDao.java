package com.warehouse.dao.domain.documentsDaoHibernate;

import com.warehouse.dao.hibernateDao.CrudHibernateDao;
import com.warehouse.model.ArrivalOfProduct;


import java.util.List;

public interface DocumentsArrivalHibDao extends CrudHibernateDao<ArrivalOfProduct, Integer> {
    List<ArrivalOfProduct> findAll();
   ArrivalOfProduct findById(Integer id);
}
