package ru.seoweblab.dao.domain.documentsDaoHibernate;

import ru.seoweblab.dao.hibernateDao.CrudHibernateDao;
import ru.seoweblab.model.MovingOfProduct;

import java.util.List;

public interface DocumentsMovingHibDao extends CrudHibernateDao<MovingOfProduct, Integer> {
    List<MovingOfProduct> findAll();
     MovingOfProduct findById(Integer id);

}
