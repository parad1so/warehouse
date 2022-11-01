package ru.seoweblab.dao.domain.documentsDaoJdbc;

import ru.seoweblab.dao.jdbcDao.CrudDao;
import ru.seoweblab.model.SaleOfProduct;


import java.util.List;

public interface DocumentsSaleDao extends CrudDao<SaleOfProduct> {
    List<SaleOfProduct> findAll();

}
