package com.warehouse.dao.domain.documentsDaoHibernate;

import com.warehouse.dao.hibernateDao.CrudHibernateDao;
import com.warehouse.model.MovingOfProduct;

import java.util.List;

public interface DocumentsMovingHibDao extends CrudHibernateDao<MovingOfProduct, Integer> {
    List<MovingOfProduct> findAll();
     MovingOfProduct findById(Integer id);

}
