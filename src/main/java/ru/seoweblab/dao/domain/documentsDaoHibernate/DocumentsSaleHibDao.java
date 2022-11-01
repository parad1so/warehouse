package ru.seoweblab.dao.domain.documentsDaoHibernate;



import ru.seoweblab.dao.hibernateDao.CrudHibernateDao;
import ru.seoweblab.model.SaleOfProduct;

import java.util.List;

public interface DocumentsSaleHibDao extends CrudHibernateDao<SaleOfProduct, Integer> {
    List<SaleOfProduct> findAll();
    SaleOfProduct findById(Integer id);
}
