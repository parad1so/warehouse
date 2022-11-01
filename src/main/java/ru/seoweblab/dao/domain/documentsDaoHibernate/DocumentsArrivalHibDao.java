package ru.seoweblab.dao.domain.documentsDaoHibernate;

import ru.seoweblab.dao.hibernateDao.CrudHibernateDao;
import ru.seoweblab.model.ArrivalOfProduct;


import java.util.List;

public interface DocumentsArrivalHibDao extends CrudHibernateDao<ArrivalOfProduct, Integer> {
    List<ArrivalOfProduct> findAll();
   ArrivalOfProduct findById(Integer id);
}
