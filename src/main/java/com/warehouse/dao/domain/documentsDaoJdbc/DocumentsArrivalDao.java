package com.warehouse.dao.domain.documentsDaoJdbc;

import com.warehouse.dao.jdbcDao.CrudDao;
import com.warehouse.model.ArrivalOfProduct;

import java.util.List;

public interface DocumentsArrivalDao extends CrudDao<ArrivalOfProduct> {
    List<ArrivalOfProduct> findAll();

}
