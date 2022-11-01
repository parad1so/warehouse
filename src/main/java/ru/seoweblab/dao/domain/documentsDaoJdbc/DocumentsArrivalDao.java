package ru.seoweblab.dao.domain.documentsDaoJdbc;

import ru.seoweblab.dao.jdbcDao.CrudDao;
import ru.seoweblab.model.ArrivalOfProduct;

import java.util.List;

public interface DocumentsArrivalDao extends CrudDao<ArrivalOfProduct> {
    List<ArrivalOfProduct> findAll();

}
